#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os,sys
from subprocess import call, PIPE, Popen, TimeoutExpired
import time, datetime, signal
#from subprocess import STDOUT, check_output
from threading import Timer
import shlex
import subprocess, threading
from time import monotonic as timer

examples= os.environ['DROIDSAFE_SRC_HOME']  + "/android-apps/examples/"

def run(apk, cmd, timeout_sec,timeoutFile):
	start = timer()
	with Popen(cmd, shell=True, stdout=PIPE, preexec_fn=os.setsid) as process:
		try:
		    output = process.communicate(timeout=timeout_sec)[0]
		except TimeoutExpired:
		    os.killpg(os.getpgid(process.pid), signal.SIGINT) #Send the signal to all the process groups
		    timeoutFile.write(apk +'\n')
		    output = process.communicate()[0]
	print('Elapsed seconds: {:.2f}s'.format(timer() - start))
	#runtimeFile = open( examples+ apk[:apk.rfind(".")] + '/runtime', 'w')	
	#runtimeFile.write('Elapsed seconds: {:.2f}s'.format(timer() - start))


if __name__ == '__main__':
	
	if len(sys.argv) < 1:
		print("Usage: python batch_dyn_analysis.py apkDir")
	appDir = sys.argv[1].rstrip('/')
	
	apks = os.listdir(appDir)

	num=0

	timeoutFile = open('timeout_file.txt','a')	

	for apk in apks:
		apkFullPath = appDir + "/" + apk

		apkName = apk[:apk.rfind(".")]
		print ("\n\nstart analysing " + apkName)
							
		command = './muDep.sh '+apkName+' '+apkFullPath
		print("command " + command)

		timeout = 20 * 60

		run(apk, command, timeout, timeoutFile);
		num = num + 1
		
	#f = open('analysed.txt','w')
	print (str(num)+" Applicaton has been analysed. DroidSafe analysis finished");



