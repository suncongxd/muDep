# -*- coding:utf-8 -*-

import os
import subprocess
import shutil

# 获取所有需要分析的二进制文件路径
ELF_PATH  = "J:\\APK\\CICInvesAndMal2019\\sos"

ida64_path = "C:\\Program Files\\IDA 7.0\\ida64.exe"

arm32_plugin_path = "F:\\argus-saf\\IDA\\pythonPlugins\\IDA\\armeabi-v7a\\SourceSinkInvoke_kind.py"
arm64_plugin_path = "F:\\argus-saf\\IDA\\pythonPlugins\\IDA\\arm64-v8a\\SourceSinkInvoke_kind.py"
x86_plugin_path = "F:\\argus-saf\\IDA\\pythonPlugins\\IDA\x86\\SourceSinkInvoke_kind.py"
x64_plugin_path = "F:\\argus-saf\\IDA\\pythonPlugins\\IDA\x64\\SourceSinkInvoke_kind.py"

order = ["armeabi", "armeabi-v7a", "x86", "mips", "arm64-v8a", "x86_64", "mips64"]

def makeDir(path):
    if not os.path.exists(path):
        os.makedirs(path)
#libartemis.so
num=0
for apk in os.listdir(ELF_PATH):
    lib_dir = os.path.join(ELF_PATH, apk, "lib")
    has_arm32 = False
    if not os.path.exists(lib_dir): 
        continue
        
    #if arch in order:
    if "armeabi" in os.listdir(lib_dir):
        has_arm32 = True
        armeabi_dir = os.path.join(lib_dir, "armeabi")
        for so in os.listdir(armeabi_dir):
            if ".so" in so:
                print "processing so: "+so
                cmd = "{0} -LD:/mylog.log -c -A -S{1} {2}".format(ida64_path, arm32_plugin_path, os.path.join(armeabi_dir, so))
                #cmd = IDA_PATH + " -c -A -S"+arm32_plugin_path+" "+elf
                subprocess.call(cmd)
        continue; #多个架构保证只有一个SSInNative.txt
    elif "armeabi-v7a" in os.listdir(lib_dir):
        has_arm32 = True
        armeabi_dir = os.path.join(lib_dir, "armeabi-v7a")
        for so in os.listdir(armeabi_dir):
            if ".so" in so:
                print "processing so: "+so
                cmd = "{0} -LD:/mylog.log -c -A -S{1} {2}".format(ida64_path, arm32_plugin_path, os.path.join(armeabi_dir, so))
                #cmd = IDA_PATH + " -c -A -S"+arm32_plugin_path+" "+elf
                subprocess.call(cmd)
        continue; #多个架构保证只有一个SSInNative.txt
    elif "x86" in os.listdir(lib_dir):
        x86_dir = os.path.join(lib_dir, "x86")
        for so in os.listdir(x86_dir):
            if ".so" in so:
                print "processing so: "+so
                cmd = "{0} -LD:/mylog.log -c -A -S{1} {2}".format(ida64_path, x86_plugin_path, os.path.join(x86_dir, so))
                subprocess.call(cmd)
        continue; #多个架构保证只有一个SSInNative.txt
    elif "x86_64" in os.listdir(lib_dir):
        x64_dir = os.path.join(lib_dir, "x86_64")
        for so in os.listdir(x64_dir):
            if ".so" in so:
                print "processing so: "+so
                cmd = "{0} -LD:/mylog.log -c -A -S{1} {2}".format(ida64_path, x64_plugin_path, os.path.join(x64_dir, so))
                subprocess.call(cmd)
        continue; #多个架构保证只有一个SSInNative.txt
    elif "arm64-v8a" in os.listdir(lib_dir):
        arm64_dir = os.path.join(lib_dir, "arm64-v8a")
        for so in os.listdir(arm64_dir):
            if ".so" in so:
                print "processing so: "+so
                cmd = "{0} -LD:/mylog.log -c -A -S{1} {2}".format(ida64_path, arm64_plugin_path, os.path.join(arm64_dir, so))
                subprocess.call(cmd)
        continue; #多个架构保证只有一个SSInNative.txt
    #if not has_arm32:
    #    print apk+" don't has arm32 so file"
    #    num += 1
#print str(num) + " apks don't has arm32 so file"
        
only_txt_path = os.path.join(os.path.dirname(ELF_PATH), "txt_results")
makeDir(only_txt_path)

for apk in os.listdir(ELF_PATH):
    lib_dir = os.path.join(ELF_PATH, apk, "lib")
    if not os.path.exists(lib_dir):
        continue
    for arch in os.listdir(lib_dir):
        if os.path.exists(os.path.join(lib_dir, arch, "SSInNative.txt")):
            makeDir(os.path.join(only_txt_path, apk))
            makeDir(os.path.join(only_txt_path, apk, arch))
            shutil.copy(os.path.join(lib_dir, arch, "SSInNative.txt"), os.path.join(only_txt_path, apk, arch))
    

