#!/bin/bash

device_name="muDep_emulator"
sdcard_img=${HOME}"/Android/sdcard.img"
#环境需要提前配置好

mksdcard -l sdcard 512M $sdcard_img


#启动虚拟机,只需要一次
emulator -avd $device_name -sdcard $sdcard_img &


