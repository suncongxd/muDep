# -*- coding:utf-8 -*-

from idaapi import *
from idautils import *
from idc import *
import re
import datetime,time

def getParent(path, levels = 1): 
    common = path
    for i in range(levels + 1): 
        common = os.path.abspath(os.path.join(common, os.pardir))
    return common

ssInNative = "SSInNative.txt"
jumpInstrction = ["blx", "bl", "b", "bx", "b.w", "call", "jmp"]
func2Strign = {}
TaintSourcesAndSinksPath = os.path.join(getParent(__file__, 3), 'sources_sinks\TaintSourcesAndSinks.txt')
#print(TaintSourcesAndSinksPath)
#TaintSourcesAndSinksPath="F:\executor\source&sink\TaintSourcesAndSinks.txt"

entryFunction_add =[ entry[2] for entry in Entries()]



class IDAMethod:
    def __init__(self, name, address):
        self.methodName = name
        self.address = address
        self.kind = "";
    def setKind(self, kind):
        self.kind = kind;


def isCallMth(ref_funcname):
    phoneNumRegex = re.compile(r'JNIEnv.*Call.*Method')
    match = phoneNumRegex.search(ref_funcname)
    return match


def readfile(file_path):
    signatures = {}

    f = open(file_path,'r')
    for line in f:
        methodName = getMthName(line)
        if methodName not in signatures.keys():
            signatures[methodName] = list()
        signatures[methodName].append(line)

    f.close()

    return signatures


def getMthName(mthSig):
    temp = mthSig.split(";.")[1]
    methodName = temp[0 : temp.find(":")]
    return methodName

def getPackageAndParaAndType(mthSig):
    temp0 = mthSig.split(";.")[0]
    packege = temp0[1:]
    temp1 = mthSig.split(";.")[1]
    tempstr = temp1.split(":")[1]
    s = tempstr.split(" ")
    paras = s[0]
    type = s[3].replace('\n', '').replace('\r', '')
    kind = s[1]
    return packege, paras, type, kind


def assmebleString():
    source_mth = set()
    sink_mth = set()
    sourceMths = readfile(TaintSourcesAndSinksPath)
    for k,strs in func2Strign.items():
        for idaStr in strs:
            for mthName,mthSigs in sourceMths.items():
                if idaStr == mthName:
                    for mthSig in mthSigs:
                        package, paras, type, kind = getPackageAndParaAndType(mthSig)
                        if exist(strs, package) and exist(strs,paras):
                            k.setKind(kind)
                            k.methodName = k.methodName + "@" + kind
                            print "kind: "+kind
                            if type == "_SOURCE_":
                                source_mth.add(k)
                            elif type == '_SINK_':
                                sink_mth.add(k)
    return source_mth, sink_mth

def exist(strs, compare):
    for str in strs:
        if compare == str:
            strs.remove(str)
            return True
    return False
    
def recursive(address, frm, nativeMth, depth):
    if depth>= 10 : 
        return
    func_name = GetFunctionName(address)
    if address in entryFunction_add: #GetFunctionFlags(address)  & FUNC_FRAME: #是函数入口（C++实现中声明的函数）
        if func_name != "" : 
            nativeMth.add( IDAMethod(func_name,frm) )
        depth -= 1
        return
    xrefs = XrefsTo(address, 0)
    refList = list(xrefs)
    print "logging refList:"+str(len(refList))
    #if func_name in exports:
    if len(refList) == 0:#叶子节点，export函数
        if func_name != "" : 
            nativeMth.add( IDAMethod(func_name,frm) )
        depth -= 1
        return

    for add in refList:
        if isCode(GetFlags(add.frm)):
            function_head = GetFunctionAttr(add.frm, idc.FUNCATTR_START)
            print "logging function_head: "+hex(function_head)
            recursive(function_head, add.frm, nativeMth, depth+1)

def recursiveSource(address, frm, nativeMth, depth):
    if depth>= 10 : 
        return
    func_name = GetFunctionName(address)
    if address in entryFunction_add: #GetFunctionFlags(address)  & FUNC_FRAME: #是函数入口（C++实现中声明的函数）
        nativeMth.add( (func_name,frm) )
        depth -= 1
        return
    xrefs = XrefsTo(address, 0)
    refList = list(xrefs)
    print "refList:"+str(len(refList))
    
    if len(refList) == 0:#叶子节点，export函数
        nativeMth.add( (func_name,frm) )
        depth -= 1
        return

    for add in refList:
        if isCode(GetFlags(add.frm)):
            function_head = GetFunctionAttr(add.frm, idc.FUNCATTR_START)
            print "function_head: "+hex(function_head)
            recursiveSource(function_head, add.frm, nativeMth, depth+1)
def genFunc2String():
    androidLogCallMth = set()
    for functionAddr in Functions():# Check each function, find JNIXXXcallXXXmethod
        
        func_name = GetFunctionName(functionAddr)
        if isCallMth(func_name) and SegName(functionAddr) == ".plt":
            print func_name
            ssCallMth = set()
            recursiveSource(functionAddr,functionAddr, ssCallMth, 0)#找到export函数,以及blx
            print "len(ssCallMth):"+str(len(ssCallMth))
            
            rodataStart, rodataEnd = roDataSeg()
            # Iterate over each cross-reference
            for ss in ssCallMth:
                #print hex(ss[1]) +"---"+ ss[0]
                # Check to see if this cross-reference is a function call
                if GetMnem(ss[1]).lower() in jumpInstrction:
                    print "callObjectMth:"+hex(ss[1]) +"---"+ ss[0]
                    #需要知道参数个数 find args

                    function_head = GetFunctionAttr(ss[1], idc.FUNCATTR_START)
                    calllerName = GetFunctionName(function_head)
                    print "caller function address : "+ hex(function_head)
                    
                    callsite = ss[1]
                    stringConstants = []
                    idaMth = IDAMethod(calllerName, function_head)
                    addr = rodataStart
                    
                    while addr < rodataEnd:
                        for xref in XrefsTo(addr, 0):
                            if function_head < xref.frm and xref.frm < callsite:
                                if GetString(addr) != None:
                                    print "add str:" + GetString(addr)
                                    stringConstants.append(GetString(addr))
                        addr = idc.NextAddr(addr)#注意这里，nextHead检测不到.data的unk_C59
                    func2Strign[idaMth] = stringConstants            
        if func_name == ".__android_log_print" and SegName(functionAddr) == ".plt":
            print "__android_log_print: "+hex(functionAddr)
            recursive(functionAddr,functionAddr, androidLogCallMth, 0)
            # print len(nativeMth)
    return androidLogCallMth  
    
def roDataSeg():
    rodataStart = 0
    rodataEnd = 0
    for i in Strings():
        if SegName(i.ea) == ".rodata":
            rodataStart = SegStart(i.ea)
            rodataEnd = SegEnd(i.ea)
            break;
    return rodataStart, rodataEnd
    
def get_string(addr):
    out = ""
    while True:
        if Byte(addr) != 0:
            out += chr(Byte(addr))
        else:
            break
        addr += 1
    return out           

def exportPoint(method, path, res, depth):
    if depth>= 10 : 
        return
    xrefs = CodeRefsTo(method.address, True)
    refList = list(xrefs)
    if len(refList) == 0:
        path.append(method)
        res.append(path)
        depth -= 1
        return

    path.append(method)

    for add in refList:
        function_head = GetFunctionAttr(add, idc.FUNCATTR_START)
        # if fd
        if SegName(function_head) == ".plt":
            #print hex(function_head)+"---func name" +GetFunctionName(function_head) +"--segname:"+SegName(function_head)
            for r in CodeRefsTo(function_head, True):
                print "1---"+hex(r)+"---func name" +GetFunctionName(r) +"--segname:"+SegName(r)
                ref_function_head = GetFunctionAttr(r, idc.FUNCATTR_START)
                callerMethod = IDAMethod(GetFunctionName(ref_function_head)+"@"+method.kind, ref_function_head)
                exportPoint(callerMethod, path, res, depth+1)

        elif SegName(function_head) == ".text":
            print "2---"+hex(function_head)+"---func name" +GetFunctionName(function_head) +"--segname:"+SegName(function_head)
            callerMethod = IDAMethod(GetFunctionName(function_head)+"@"+method.kind, function_head)
            exportPoint(callerMethod, path, res, depth+1)


def printPath(mth):
    mth2Path = {}
    for m in mth:
        path = list()
        res = list()

        #path.append(source)
        #print "source address:"+hex(source.address)
        exportPoint(m,path,res, 0)

        mth2Path[m] = res
    return mth2Path

#每个apk单独写结果
def writeFile(source_mth, sink_mth, androidLogCallMth, runtime):
    
    #if os.path.exists(ssInNative):
    #    os.remove(ssInNative)
        
    if(len(source_mth) ==0 and len(sink_mth) == 0 and len(androidLogCallMth) == 0):
        return 
    print "writing results...."
    f = open(ssInNative,"a+")
    
    f.write("source: ")
    for source in source_mth:
        f.write(source.methodName+"; ")
    f.write("\n")
    
    f.write("sink: ")
    for sink in sink_mth:
        f.write(sink.methodName + "; ")
    f.write("\n")
    
    f.write("android_log_print: ")
    for n in androidLogCallMth:
        f.write(n.methodName + "; ")
    f.write("\n")
    
    #f.write("runtime: " + str(round(runtime, 9)))
    f.write("runtime: " + int(runtime))
    f.write("\n")

    f.close()
    
def JNI_onload_resolve(methods):
    entryFunction_name =[ entry[3] for entry in Entries()]
    #for entry in Entries():  #(index, ordinal, ea, name)
    if "JNI_OnLoad" in entryFunction_name:
        print "JNI_OnLoad exist. resolveing native function now..."
        for mth in methods:
            #print "invoke address"+hex(mth[1])
            function_head = GetFunctionAttr(mth.address, idc.FUNCATTR_START)
            print "entry address:"+hex(function_head)
            
            xrefs = XrefsTo(function_head, 0) 
            for xref in xrefs:
                addr = xref.frm
                #print "called by address:"+ hex(addr)
                if SegName(addr) == ".data":
                    #print "data address:"+hex(addr)
                    addr = idc.PrevHead(addr)#走一步是方法参数名
                    mthParas = getString(addr)
                    
                    addr = idc.PrevHead(addr)#走两步是方法名
                    #print "forward two step address:"+hex(addr)
                    mthName = getString(addr)
                    
                    if mthName != "":
                        mth.methodName= "dynamic_register#" + mthName + mthParas + "@" + mth.kind
                            
def getString(addr):
    refs = XrefsFrom(addr,0)
    for ref in refs:
        #print "ref.to:"+hex(ref.to)
        address = ref.to
        if GetString(address) != None:
            print "data:"+hex(address) + "---content:" +GetString(address)
            name = GetString(address)#动态注册方法名
            return name
    return ""                        
                  
if __name__ == '__main__':
    
    starttime = time.time()
    
    # Wait for auto-analysis to finish before running script
    idaapi.autoWait()
    
    androidLogCallMth = genFunc2String()
    source_mth, sink_mth = assmebleString()

    #long running
    runtime= time.time() - starttime
    
    #输出路径
    source2Path = printPath(source_mth)
    sink2Path = printPath(sink_mth)
    
    #print "source_mth len:"+str(len(source_mth)) #source_mth是.text地址
    final_source = set()
    final_sink = set()
    print "source method path......"
    for source,paths in source2Path.items():
        print "paths len:" + str(len(paths))
        for path in paths:
            final_source.add(path[len(path)-1])
            print "mth len:" + str(len(path))
            for mth in path:
                print mth.methodName + "---" + hex(mth.address) + " -> "
                

    print "sink method path......"
    for sink,paths in sink2Path.items():

        print "paths len:" + str(len(paths))
        
        for path in paths:
            final_sink.add(path[len(path)-1])
            print "mth len:" + str(len(path))
            for mth in path:
                print mth.methodName + "---" + hex(mth.address) + " -> "
                
                
    print "android_log_print........"
    for n in androidLogCallMth:
            print "__android_log_print method called by method: "+n.methodName
            
            
    JNI_onload_resolve(androidLogCallMth)
    JNI_onload_resolve(final_source)
    JNI_onload_resolve(final_sink)
    
    #写结果到文件
    writeFile(final_source, final_sink, androidLogCallMth, runtime)
    
    idc.Exit(0)
