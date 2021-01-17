# -*- coding:utf-8 -*-

import os
import subprocess
import time,datetime

apk_dir = os.path.join(os.path.dirname(os.getcwd()), 'apks')
result_path = os.path.join(os.getcwd(), 'so')
#result_path = os.path.join(os.path.dirname(os.getcwd()), 'so')

if not os.path.exists(result_path):
    os.mkdir(result_path)

num=0
for f in os.listdir(apk_dir):
    if f[len(f)-4:] == ".apk" :
        num = num + 1 
        apkName = f[0:len(f)-4]
        cmd = "unzip {0} lib/*.so -d {1}".format(os.path.join(apk_dir, f), os.path.join(result_path,apkName))
        subprocess.call(cmd,shell=True)

print('Extracted ' + str(num) + ' apk file') 
