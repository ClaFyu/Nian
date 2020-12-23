import xlrd
from dbUtil import insert_form


def read_excel(filename):
    wb = xlrd.open_workbook(filename)
    table = wb.sheets()[0]
    n_rows = table.nrows

    for i in range(1, n_rows):
        ss = table.row_values(i)

        member_name = ss[0]
        member_num = ss[1]
        department_name = ss[2]

        insert_form(member_name, member_num, department_name)
