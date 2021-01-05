# -*- coding:utf-8 -*-

import idc
import idaapi
import idautils
'''
sid = idc.GetStrucIdByName('JNINativeMethod')
print sid
for xref in idautils.XrefsTo(sid):
    print "xref.frm:"+hex(xref.frm)
    if 0xFF000000 > xref.frm:
        # The struct is used as data
        print 'Data xref from 0x{:X}'.format(xref.frm)
    else:
        # The struct is used as a stack variable.
        mid = xref.frm
        fullname = idaapi.get_member_fullname(mid)
        function_ea = int(fullname[2:].split('.')[0], 0x10)
        print 'Stack xref from 0x{:X}'.format(function_ea)
'''
'''
print "-----------"
xrefs = XrefsTo(0x7adL,0)
for xref in xrefs:
    #print hex(xref.frm)
    addr = xref.frm
    #print "called by address:"+ hex(addr)
    if SegName(addr) == ".data":
        print "data address:"+hex(addr)
        addr = idc.PrevHead(addr)
        addr = idc.PrevHead(addr)#走两步
        print "forward two step address:"+hex(addr)
        
        ref_address =  GetOperandValue(addr, 0)
        print "GetMnem:"+GetMnem(addr)
        print "GetOperand 1 Value :"+hex(ref_address)
        
        sdf = XrefsFrom(addr,0)
        for ref in sdf:
            ref_address =  GetOperandValue(ref.to, 0)
            print "ref.to:"+hex(ref.to)
            if GetString(ref.to) != None:
                print "data:"+hex(addr) + "---content:" +GetString(ref.to)



xrefs = XrefsFrom(0x3010L,0)
for xref in xrefs:
    print "XrefsFrom:"+hex(xref.to)
'''

entryFunction_add =[ entry[3] for entry in Entries()]

for add in entryFunction_add:
    print add
