import xlwt
from dbUtil import search_form, search_award_abbr, search_department_abbr,\
    search_award_fullname, get_num_of_department, search_award


def write_member_excel(department, award):
    workbook = xlwt.Workbook(encoding='utf-8')
    worksheet = workbook.add_sheet('awardList')

    style = xlwt.XFStyle()
    font = xlwt.Font()
    font.name = 'Times New Roman'
    style.font = font

    worksheet.write(0, 0, '员工姓名', style)
    worksheet.write(0, 1, '员工工号', style)
    worksheet.write(0, 2, '员工所在部门', style)
    worksheet.write(0, 3, '员工所获奖项', style)

    i = 1   # 表示正式写入数据是从第二行开始的，下标为1

    number_of_department = get_num_of_department()
    department_name = search_department_abbr(department)
    award_name = search_award_abbr(award)

    result = search_form(number_of_department, department_name, award_name)

    for r in result:

        worksheet.write(i, 0, r[0], style)
        worksheet.write(i, 1, r[1], style)
        worksheet.write(i, 2, r[2], style)
        worksheet.write(i, 3, search_award_fullname(r[3]), style)

        i += 1

    workbook.save(department + "_" + award + ".xls")


def write_award_excel():
    workbook = xlwt.Workbook(encoding='utf-8')
    worksheet = workbook.add_sheet('awardList')

    style = xlwt.XFStyle()
    font = xlwt.Font()
    font.name = 'Times New Roman'
    style.font = font

    i = 1

    worksheet.write(0, 0, '奖项名称', style)
    worksheet.write(0, 1, '奖项设置人姓名', style)
    worksheet.write(0, 2, '奖项设置人工号', style)
    worksheet.write(0, 3, '奖项单位金额', style)
    worksheet.write(0, 4, '奖项总中奖人数', style)

    result = search_award()

    for r in result:
        worksheet.write(i, 0, r[0], style)
        worksheet.write(i, 1, r[1], style)
        worksheet.write(i, 2, r[2], style)
        worksheet.write(i, 3, r[3], style)
        worksheet.write(i, 4, r[4], style)

    workbook.save("额外奖项清单.xls")
