import sys
import os

from importFile import read_excel
from exportFile import write_member_excel, write_award_excel
from dbUtil import search_all_award, search_all_department


if __name__ == '__main__':
    # 导入名单
    if sys.argv[1] is "0":
        if len(sys.argv) != 3 or os.path.isfile(sys.argv[2]) is False:
            print("Usage:\npython main.py 0 [uploadFileName] or "
                  "python main.py 1 [departmentName] [awardName]")
        else:
            read_excel(sys.argv[2])
    # 按需导出获奖名单
    elif sys.argv[1] is "1":
        if len(sys.argv) != 4:
            print("Usage:\npython main.py 0 [uploadFileName] or "
                  "python main.py 1 [departmentName] [awardName]")
        else:
            write_member_excel(sys.argv[2], sys.argv[3])
    # 导出领导设置的奖项
    elif sys.argv[1] is "2":
        if len(sys.argv) != 2:
            print("Usage:\npython main.py 0 [uploadFileName] or "
                  "python main.py 1 [departmentName] [awardName]")
    # 查看当前数据库中包含的部门和奖项名称，用于导出获奖名单
    elif sys.argv[1] is "3":
        if len(sys.argv) != 2:
            print("Usage:\npython main.py 0 [uploadFileName] or "
                  "python main.py 1 [departmentName] [awardName]")

        r1 = search_all_department()
        r2 = search_all_award()

        print("部门为：")
        for r in r1:
            print(r)
        print("\n")

        print("奖项为：")
        for r in r2:
            print(r)
    # 其他情况一律看作无效行为
    else:
        print("Usage:\npython main.py 0 [uploadFileName] or "
              "python main.py 1 [departmentName] [awardName]")
