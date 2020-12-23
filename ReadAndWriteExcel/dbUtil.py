import pymysql
from config import *


def search_all_department():
    db = pymysql.connect(url, username, passwd, table)
    cursor = db.cursor()

    result = []

    cursor.execute("select name from department")
    res = cursor.fetchall()

    for r in res:
        result.append(r[0])

    db.close()

    return result


def search_all_award():
    db = pymysql.connect(url, username, passwd, table)
    cursor = db.cursor()

    result = []

    cursor.execute("select name from award")
    res = cursor.fetchall()

    for r in res:
        result.append(r[0])

    db.close()

    return result


def insert_form(member, number, department):
    db = pymysql.connect(url, username, passwd, table)
    cursor = db.cursor()

    try:
        cursor.execute("select abbr from department where name = '%s';" % department)
        results1 = cursor.fetchall()

        if len(results1) == 0:
            cursor.execute("select count(abbr) from department")
            results2 = cursor.fetchall()

            n_name = num_to_str_n(results2[0][0] + 1, 2)
            cursor.execute("insert into department(abbr, name, numberOfMember) "
                           "values ('%s', '%s', 1);" % (n_name, department))
            db.commit()

            cursor.execute("insert into member(name, jobNumber, department) "
                           "values ('%s', '%s', '%s');" % (member, str(int(number)), n_name))
            db.commit()
        else:
            cursor.execute("select id from member where name = '%s' and department = '%s';"
                           % (member, results1[0][0]))
            results2 = cursor.fetchall()

            if len(results2) != 0:
                raise Exception("Existed data", member)
            else:
                cursor.execute("insert into member(name, jobNumber, department) "
                               "values ('%s', '%s', '%s');" % (member,
                                                               str(int(number)),
                                                               results1[0][0]))
                db.commit()

                cursor.execute("update department set numberOfMember = numberOfMember + 1 "
                               "where abbr = '%s';" % results1[0][0])
                db.commit()
    except Exception:
        print("Error: unable to fetch data")
        db.rollback()
    finally:
        db.close()


def get_num_of_department():
    db = pymysql.connect(url, username, passwd, table)
    cursor = db.cursor()

    cursor.execute("select count(abbr) from department;")
    result = cursor.fetchall()

    return result[0][0]


def search_department_abbr(department):
    if department == "所有部门":
        return "00"

    db = pymysql.connect(url, username, passwd, table)
    cursor = db.cursor()

    cursor.execute("select abbr from department where name = '%s';" % department)
    result = cursor.fetchall()
    db.close()

    return result[0][0]


def search_award_abbr(award):
    if award == "所有奖项":
        return "all"

    db = pymysql.connect(url, username, passwd, table)
    cursor = db.cursor()

    cursor.execute("select abbr from award where name = '%s';" % award)
    result = cursor.fetchall()
    db.close()

    return result[0][0]


def search_department_fullname(abbr):
    db = pymysql.connect(url, username, passwd, table)
    cursor = db.cursor()

    cursor.execute("select name from department where abbr = '%s';" % abbr)
    result = cursor.fetchall()[0][0]
    db.close()

    return result


def search_award_fullname(abbr):
    if abbr is None:
        return ""

    db = pymysql.connect(url, username, passwd, table)
    cursor = db.cursor()

    cursor.execute("select name from award where abbr = '%s';" % abbr)
    result = cursor.fetchall()[0]
    db.close()

    return result


def search_form(number, department, award):
    db = pymysql.connect(url, username, passwd, table)
    cursor = db.cursor()

    result = []

    if department == "00" and award == 'all':  # 针对全部门、所有奖项
        for i in range(number):
            department_name = search_department_fullname(num_to_str_n(i + 1, 2))

            cursor.execute("select * from member where department='%s' and flagOfAward is not null;"
                           % num_to_str_n(i + 1, 2))
            res = cursor.fetchall()
            for r in res:
                result.append([r[1], r[2], department_name, r[4]])
    elif department == "00":  # 针对全部门、特定奖项
        for i in range(number):
            department_name = search_department_fullname(num_to_str_n(i + 1, 2))

            cursor.execute("select * from member where department='%s' and flagOfAward = '%s';"
                           % (num_to_str_n(i + 1, 2), award))
            res = cursor.fetchall()

            for r in res:
                result.append([r[1], r[2], department_name, r[4]])
    elif award == 'all':  # 针对特定部门、所有奖项
        department_name = search_department_fullname(department)
        cursor.execute("select * from member where department='%s' and flagOfAward is not null;"
                       % department)
        res = cursor.fetchall()

        for r in res:
            result.append([r[1], r[2], department_name, r[4]])
    else:  # 针对特定部门、特定奖项
        department_name = search_department_fullname(department)
        cursor.execute("select * from member where department='%s' and flagOfAward = '%s';"
                       % (department, award))
        res = cursor.fetchall()

        for r in res:
            result.append([r[1], r[2], department_name, r[4]])

    db.close()
    return result


def search_award():
    db = pymysql.connect(url, username, passwd, table)
    cursor = db.cursor()

    result = []

    cursor.execute("select name, leader, leaderJobNumber, moneyPerPerson, totalNumberMember "
                   "from award where leader is not null and leaderJobNumber is not null;")
    res = cursor.fetchall()

    for r in res:
        result.append([r[0], r[1], r[2], r[3], r[4]])

    return result
