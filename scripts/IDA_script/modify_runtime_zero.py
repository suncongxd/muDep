import os
import sys

def read_lines_to_list(path):
    lines_list=[]
    with open(path,"r",encoding="utf-8") as file:
        for line in file.readlines():
            lines_list.append(line.strip("\n"))
    return lines_list

def write_list_to_file(file_list,file_path,mode):
    with open(file_path,mode,encoding="utf-8") as file:
        for line in file_list:
            file.write(line+"\n")

if __name__ == '__main__':
    if len(sys.argv) < 2:
        print("请输入待处理的目录：")

    folder = sys.argv[1]
    for apk in os.listdir(folder):
        for type in os.listdir(folder+"/"+apk):
            temp_content=read_lines_to_list(folder+"/"+apk+"/"+type+"/SSInNative.txt")
            result=[]
            for line in temp_content:
                if line.startswith("runtime: "):
                    result.append("runtime: 0\n")
                else:
                    result.append(line)
            write_list_to_file(result,folder+"/"+apk+"/"+type+"/SSInNative.txt","w")