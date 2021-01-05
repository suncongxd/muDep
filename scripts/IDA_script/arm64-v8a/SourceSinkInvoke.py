# -*- coding:utf-8 -*-

from idaapi import *
from idautils import *
from idc import *
import re
import datetime,time



ssInNative = "SSInNative.txt"
jumpInstrction = ["blx", "bl", "b", "bx", "call"]
func2Strign = {}
TaintSourcesAndSinksPath="F:\executor\source&sink\TaintSourcesAndSinks.txt"
#TaintSourcesAndSinksPath="/media/myw/Study/运行/source&sink/TaintSourcesAndSinks.txt"




class IDAMethod:
    def __init__(self, name, address):
        self.methodName = name
        self.address = address


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
    return packege, paras, type


def assmebleString():
    source_mth = set()
    sink_mth = set()
    sourceMths = readfile(TaintSourcesAndSinksPath)
    
    for k,strs in func2Strign.items():
        print "len(strs):"+str(len(strs))
        for idaStr in strs:
            print "idaStr:"+idaStr
            for mthName,mthSigs in sourceMths.items():
                if idaStr == mthName:
                    for mthSig in mthSigs:
                        package, paras, type= getPackageAndParaAndType(mthSig)
                        if exist(strs, package) and exist(strs,paras):

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
    
def recursive(address, nativeMth):

    func_name = GetFunctionName(address)
    print "recursive func_name:"+func_name
    #xrefs = 
    refList = list(CodeRefsTo(address, 1))
    print "refList:"+str(len(refList))
    if len(refList) == 0:#叶子节点，export函数
        print "add.....-----" + func_name
        nativeMth.add(func_name)
        return

    for add in refList:
        print "just" + hex(add)
        if isCode(GetFlags(add)):
            function_head = GetFunctionAttr(add, idc.FUNCATTR_START)
            print "called by address: "+hex(add)
            print "function_head: "+hex(function_head)
            recursive(function_head, nativeMth)

def genFunc2String():
    androidLogCallMth = set()
    for functionAddr in Functions():
        # Check each function to look for strcpy
        func_name = GetFunctionName(functionAddr)

        if isCallMth(func_name) and SegName(functionAddr) == ".plt":
            #print func_name
            xrefs = XrefsTo(functionAddr, 0)
            # Iterate over each cross-reference
            for xref in xrefs:
                # Check to see if this cross-reference is a function call
                if GetMnem(xref.frm).lower() in jumpInstrction:
                    print "callObjectMth:"+hex(xref.frm)
                    #需要知道参数个数 find args

                    function_head = GetFunctionAttr(xref.frm, idc.FUNCATTR_START)
                    calllerName = GetFunctionName(function_head)
                    print "caller function address : "+ hex(function_head) + "---name:" + calllerName
                    addr = xref.frm
                    idaMth = IDAMethod(calllerName, function_head)
                    stringConstants = []
                    while addr > function_head:
                        addr = idc.PrevHead(addr)
                        ref_address =  GetOperandValue(addr, 2)#修改了这里
                        #print hex(addr) +"----"+ hex(ref_address)
                        if isData(GetFlags(ref_address)) and SegName(ref_address) in [".text",".rodata"]:
                            if GetString(ref_address) != None:
                                print "data:"+hex(addr) + "---content:" +GetString(ref_address)
                                stringConstants.append(GetString(ref_address))
                    func2Strign[idaMth] = stringConstants   
                    
                                
        if func_name == ".__android_log_print" and SegName(functionAddr) == ".plt":
            print hex(functionAddr)
            recursive(functionAddr, androidLogCallMth)
            # print len(nativeMth)
    return androidLogCallMth        
            

def exportPoint(method, path, res):
    xrefs = CodeRefsTo(method.address, True)
    refList = list(xrefs)
    if len(refList) == 0:
        path.append(method)
        res.append(path)
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
                callerMethod = IDAMethod(GetFunctionName(ref_function_head), ref_function_head)
                exportPoint(callerMethod, path, res)

        elif SegName(function_head) == ".text":
            print "2---"+hex(function_head)+"---func name" +GetFunctionName(function_head) +"--segname:"+SegName(function_head)
            callerMethod = IDAMethod(GetFunctionName(function_head), function_head)
            exportPoint(callerMethod, path, res)


def printPath(mth):
    mth2Path = {}
    for m in mth:
        path = list()
        res = list()

        #path.append(source)
        #print "source address:"+hex(source.address)
        exportPoint(m,path,res)

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
        f.write(n + "; ")
    f.write("\n")
    
    f.write("runtime: " + str(runtime))
    f.close()


if __name__ == '__main__':
    
    starttime = datetime.datetime.now()
    
    # Wait for auto-analysis to finish before running script
    #idaapi.autoWait()
    
    androidLogCallMth = genFunc2String()
    print "len(func2Strign):"+str(len(func2Strign))
    print "len(androidLogCallMth):"+str(len(androidLogCallMth))
    for k,strings in func2Strign.items():
        print len(strings)
        for sdf in strings:
            print sdf
    
    
    source_mth, sink_mth = assmebleString()
    print "len(source_mth):"+str(len(source_mth))
    print "len(sink_mth):"+str(len(sink_mth))
    
    #long running
    endtime = datetime.datetime.now()
    runtime= (endtime - starttime).seconds
    
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
            print "__android_log_print method called by method: "+n
    #写结果到文件
    writeFile(final_source, final_sink, androidLogCallMth, runtime)
    
    #idc.Exit(0)



