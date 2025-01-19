def time2num(time):
    return int(time[:2]) * 60+int(time[3:])

def solution(book_time):
    dict = {}
    for book in book_time:
        st = time2num(book[0])
        et = time2num(book[1])
        for t in range(st, et+10):
            if dict.get(t) == None:
                dict[t] = 1
            else:
                dict[t] = dict[t]+1
                

    answer = max(dict.values())
    return answer
