#!/bin/bash

droidsafe_home=${DROIDSAFE_SRC_HOME}

current_path=$(pwd)

mudep_root=$(dirname $(pwd))
ssNative_path=$current_path/txt_results
preworks_jar=$mudep_root/bin/preworks.jar
#preworks_jar=$current_path/preworks.jar


fuzzing_package="nativemethod.nativemethod.fuzzing"
fuzzing=$mudep_root/Fuzzing2.1

#echo "current_path:"$current_path
#echo "muDep_root:"$mudep_root
#echo "ssNative_path:"$ssNative_path
#echo "preworks.jar path:"$preworks_jar
#echo "Fuzzing2.1 path:"$fuzzing

dex2jar=$mudep_root/bin/dex2jar-2.0/d2j-dex2jar.sh

droidsafe_src_jar=$mudep_root/bin/droidsafe-source.jar

device_serial="emulator-5554"

apk_name=$1 # the apk name without ".apk"
apk_path=$(realpath $2) # the path of apk, relative path converted to absolute path


#1:模拟器未完全启动
#2:APK未安装成功
#3:APK程序运行崩溃
#4:APK正常运行，但flag.json生成时间过长，或可能出现了死循环
emulator_error=0  #正常，无错误


function emulator_error_check(){
	if [[ "$emulator_error" > "0" ]]
	then
		echo ""
	fi
}



######### Phase 1 ################################

ss_path=
function findSS(){
	apk_name=$1
	if [ -d $ssNative_path/$apk_name ]	
	then
		for arch in ` ls $ssNative_path/$apk_name `
		do  
			echo "find SSInNative.txt ..."
			if [ -f "$ssNative_path/$apk_name/$arch/SSInNative.txt" ];  then	
				ss_path=$ssNative_path/$apk_name/$arch/SSInNative.txt
			fi
		done
	fi
}

runtime=
# Phase 1
function phase1(){
	apk_path=$1
	apk_name=$2

	findSS $apk_name
	echo "ss_path:"$ss_path
	start_tm=`date +%s%N`;
	#chmod +x $preworks_jar
	#echo "java -jar "$preworks_jar" "$apk_path" "$ss_path

	java -jar $preworks_jar $apk_path $ss_path
	end_tm=`date +%s%N`;
	use_tm=`echo $end_tm $start_tm | awk '{ print ($1 - $2) / 1000000000}'`

	runtime=$use_tm
}

cd $current_path

phase1 $apk_path $apk_name
phase1_time=$runtime
echo "phase1 runtime:"$runtime

######### Phase 2 ################################

function checkEmulator(){
	status=$(adb -s $device_serial shell getprop sys.boot_completed)
	times=30
	num=0 #5min
	
	result=$(echo $status | grep "1")
	while [[ "$result" == "" ]]&&[ $num -lt $times ]
	do	
		sleep 10
		((num++))
		status=$(adb -s $device_serial shell getprop sys.boot_completed)
		result=$(echo $status | grep "1")
	done
	
	if [ $num -ge $times ]	
	then
		echo "emulator does not fully start up!"
		emulator_error=1
	fi
}


function phase2(){
	apk_path=$1
	apk_name=$2
	#分析前删除fuzzing/lib下的内容
	rm -rf $fuzzing/app/libs/*

	decompile_pathname=$fuzzing/app/libs/$apk_name.jar
	sh $dex2jar $apk_path -o $decompile_pathname --force
	7z d $decompile_pathname android

	checkEmulator  #模拟器启动完后
	if [[ "$emulator_error" > "0" ]]
	then
		echo "Emulator not fully launched!"
		return
	fi
	adb -s $device_serial shell mount -o remount rw /
	adb -s $device_serial push $apk_path /sdcard

	#解压apk,将so加载过来
	unzip  $apk_path lib/*.so 
	mv lib/* $fuzzing/app/libs/
	rm -rf lib

	#修改后自动编译并打包为apk,并安装到设备
	cd $fuzzing
	output=$(./gradlew installDebug --stacktrace)
	cd ..
	#echo "build成功后继续."
	result=$(echo $output | grep "BUILD SUCCESSFUL ")
	if [[ "$result" == "" ]]
	then
	    emulator_error=2
		echo "Install APK failed, build incomplete!"
		echo $apk_name" --- Install APK failed" >> $droidsafe_home/android-apps/examples/mutdep_fuzzing_failed.txt
		adb -s $device_serial shell rm /sdcard/$apk_name.apk
		return
	fi
	echo "gradle build success"

	start_tm=`date +%s%N`;
	#运行mainActivity
	adb -s $device_serial shell am start -n "nativemethod.nativemethod.fuzzing/nativemethod.fuzzing.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER

	#ds_app_dir=$droidsafe_home/android-apps/examples/$apk_name
	#mkdir $ds_app_dir
	#adb logcat > $droidsafe_home/android-apps/examples/$apk_name/fuzz_log.txt &

	#app是否崩溃
	#adb logcat -b crash

	#判断flag.json文件是否生成
	times=100 #10min
	result=$(adb -s $device_serial shell ls "/data/data/$fuzzing_package/files" | tr -d '\015'|grep '^finished.txt$')
	while [ -z "$result" ]&&[ $num -lt $times ]
	do
		sleep 6
		echo "flag.json is under generation ..."
		result=$(adb shell ls "/data/data/$fuzzing_package/files" | tr -d '\015'|grep '^finished.txt$')
		((num++))
	done

	if [ $num -ge $times ]	
	then
		echo "APK runs normally. flag.json generation timeout!"
		echo $apk_name" --- flag.json generation timeout" >> $droidsafe_home/android-apps/examples/mutdep_fuzzing_failed.txt 
		emulator_error=4
		#adb uninstall $fuzzing_package
		#adb shell rm /sdcard/$apk_name.apk

		flag=$(adb -s $device_serial shell ls "/data/data/$fuzzing_package/files" | tr -d '\015'|grep '^flag.json$')
		if [ -z "$flag" ]
		then
			adb -s $device_serial uninstall $fuzzing_package
			adb -s $device_serial shell rm /sdcard/$apk_name.apk	
			return
		fi
		return
	fi
	
	echo "flag.json generated."
	end_tm=`date +%s%N`;
	use_tm=`echo $end_tm $start_tm | awk '{ print ($1 - $2) / 1000000000}'`
	runtime=$use_tm

	#生成的f设备上的flag.json放到本地 
	flag_path=/data/data/$fuzzing_package/files/flag.json
	ds_app_dir=$droidsafe_home/android-apps/examples/$apk_name
	if [[ ! -d $ds_app_dir ]]; then
		mkdir $ds_app_dir
	fi
	adb -s $device_serial pull $flag_path $ds_app_dir/

	#卸载fuzzing apk
	adb -s $device_serial uninstall $fuzzing_package

	#删除sdcard的apk
	adb -s $device_serial shell rm /sdcard/$apk_name.apk
}

######### Phase 3 ################################

function matchSS(){
	ds_app_dir=$1
	if [ -d $ssNative_path/$apk_name ]	
	then
		for arch in ` ls $ssNative_path/$apk_name `
		do  
			if [ -f "$ssNative_path/$apk_name/$arch/SSInNative.txt" ];  then
				if [ ! -d $ds_app_dir/apktool-gen/lib/$arch ]; then
					mkdir -p $ds_app_dir/apktool-gen/lib/$arch
				fi
				cp $ssNative_path/$apk_name/$arch/SSInNative.txt $ds_app_dir/apktool-gen/lib/$arch/
				echo "SSInNative.txt copy to:"$ds_app_dir/apktool-gen/lib/$arch/
			fi
		done

	fi
}

function phase3(){
	apk_path=$1
	apk_name=$2

	ds_app_dir=$droidsafe_home/android-apps/examples/$apk_name
	#echo "ds_app_dir:"$ds_app_dir

	if [[ ! -d $ds_app_dir ]]; then
		mkdir $ds_app_dir
	fi
	cp $apk_path $ds_app_dir
	cd $ds_app_dir
	python $droidsafe_home/bin/unpack-apk -f $ds_app_dir/$apk_name.apk
	matchSS $ds_app_dir

	#run droidsafe
	start_tm=`date +%s%N`;
	#$droidsafe_home/bin/droidsafe -approot $ds_app_dir -apkfile $apk_name.apk -t  specdump
	java -jar $droidsafe_src_jar -approot $ds_app_dir -apkfile $apk_name.apk -t  specdump
	end_tm=`date +%s%N`;

	runtime=`echo $end_tm $start_tm | awk '{ print ($1 - $2) / 1000000000}'`
}


#combined use of phase 2 and phase 3

if [ -f result/$apk_name/app_native_mth.txt ]; then
	#mv -f result/$apk_name/app_native_mth.txt $fuzzing/app/src/main/res/raw/app_native_mth.txt
	cp result/$apk_name/app_native_mth.txt $fuzzing/app/src/main/res/raw/
	#cp result/$apk_name/implementor.txt $fuzzing/app/src/main/res/raw/ #implementor.txt

	phase2 $apk_path $apk_name
	phase2_time=$runtime
	echo "phase2 runtime:"$runtime

	flag_time=$(echo "$phase1_time+$phase2_time"|bc)
	
	
	#if [[ "$emulator_error" == "0" ]]
	#then
fi


phase3 $apk_path $apk_name
phase3_time=$runtime
echo "phase3 runtime:"$runtime
echo "droidsafe_time: "$phase3_time >> $droidsafe_home/android-apps/examples/$apk_name/droidsafe-gen/mutdep_runtime.txt #write time


