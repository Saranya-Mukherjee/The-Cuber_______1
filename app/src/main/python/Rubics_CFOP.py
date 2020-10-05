top = [['y', 'y', 'y'], ['y', 'y', 'y'], ['y', 'y', 'y']]
bot = [['w', 'w', 'w'], ['w', 'w', 'w'], ['w', 'w', 'w']]
front = [['r', 'r', 'r'], ['r', 'r', 'r'], ['r', 'r', 'r']]
left = [['b', 'b', 'b'], ['b', 'b', 'b'], ['b', 'b', 'b']]
right = [['g', 'g', 'g'], ['g', 'g', 'g'], ['g', 'g', 'g']]
back = [['o', 'o', 'o'], ['o', 'o', 'o'], ['o', 'o', 'o']]
moves = []
step = 0
trn = 0


def chap():
    print("TOP:")
    for i in range(3):
        for j in range(3):
            print(top[i][j], end='    ')
        print()
        print()
    print("BOT:")
    for i in range(3):
        for j in range(3):
            print(bot[i][j], end='    ')
        print()
        print()
    print("FRONT:")
    for i in range(3):
        for j in range(3):
            print(front[i][j], end='    ')
        print()
        print()
    print("LEFT:")
    for i in range(3):
        for j in range(3):
            print(left[i][j], end='    ')
        print()
        print()
    print("RIGHT:")
    for i in range(3):
        for j in range(3):
            print(right[i][j], end='    ')
        print()
        print()
    print("BACK:")
    for i in range(3):
        for j in range(3):
            print(back[i][j], end='    ')
        print()
        print()


def up():
    moves.append("u")
    sw = ' '
    sww = ' '
    for a in range(3):
        sw = right[0][a]
        right[0][a] = front[0][a]
        sww = back[0][a]
        back[0][a] = sw
        sw = left[0][a]
        left[0][a] = sww
        front[0][a] = sw
    sw = top[2][0]
    top[2][0] = top[0][0]
    sww = top[2][2]
    top[2][2] = sw
    sw = top[0][2]
    top[0][2] = sww
    top[0][0] = sw

    sw = top[1][0]
    top[1][0] = top[0][1]
    sww = top[2][1]
    top[2][1] = sw
    sw = top[1][2]
    top[1][2] = sww
    top[0][1] = sw

    print("U' Done")


def u():
    moves.append("U")
    sw = ' '
    sww = ' '
    for a in range(3):
        sw = left[0][a]
        left[0][a] = front[0][a]
        sww = back[0][a]
        back[0][a] = sw
        sw = right[0][a]
        right[0][a] = sww
        front[0][a] = sw
    sw = top[0][2]
    top[0][2] = top[0][0]
    sww = top[2][2]
    top[2][2] = sw
    sw = top[2][0]
    top[2][0] = sww
    top[0][0] = sw

    sw = top[1][2]
    top[1][2] = top[0][1]
    sww = top[2][1]
    top[2][1] = sw
    sw = top[1][0]
    top[1][0] = sww
    top[0][1] = sw
    print("U Done")


def r():
    moves.append("R")
    sw = ' '
    sww = ' '
    d = 2
    for a in range(3):
        sw = top[a][2]
        top[a][2] = front[a][2]
        sww = back[d][0]
        back[d][0] = sw
        sw = bot[d][0]
        bot[d][0] = sww
        d -= 1
        front[a][2] = sw
    sw = right[0][2]
    right[0][2] = right[0][0]
    sww = right[2][2]
    right[2][2] = sw
    sw = right[2][0]
    right[2][0] = sww
    right[0][0] = sw

    sw = right[1][2]
    right[1][2] = right[0][1]
    sww = right[2][1]
    right[2][1] = sw
    sw = right[1][0]
    right[1][0] = sww
    right[0][1] = sw
    print("R Done")


def rp():
    moves.append("r")
    sw = ' '
    sww = ' '
    d = 2
    for a in range(3):
        sw = bot[d][0]
        bot[d][0] = front[a][2]
        sww = back[d][0]
        back[d][0] = sw
        d -= 1
        sw = top[a][2]
        top[a][2] = sww
        front[a][2] = sw
    sw = right[2][0]
    right[2][0] = right[0][0]
    sww = right[2][2]
    right[2][2] = sw
    sw = right[0][2]
    right[0][2] = sww
    right[0][0] = sw

    sw = right[1][0]
    right[1][0] = right[0][1]
    sww = right[2][1]
    right[2][1] = sw
    sw = right[1][2]
    right[1][2] = sww
    right[0][1] = sw
    print("R' Done")


def l():
    moves.append("L")
    sw = ' '
    sww = ' '
    d = 2
    for a in range(3):
        sw = bot[d][2]
        bot[d][2] = front[a][0]
        sww = back[d][2]
        back[d][2] = sw
        d -= 1
        sw = top[a][0]
        top[a][0] = sww
        front[a][0] = sw
    sw = left[0][2]
    left[0][2] = left[0][0]
    sww = left[2][2]
    left[2][2] = sw
    sw = left[2][0]
    left[2][0] = sww
    left[0][0] = sw

    sw = left[1][2]
    left[1][2] = left[0][1]
    sww = left[2][1]
    left[2][1] = sw
    sw = left[1][0]
    left[1][0] = sww
    left[0][1] = sw
    print("L Done")


def lp():
    sw = ''
    sww = ''
    d = 2
    for a in range(3):
        sw = top[a][0]
        top[a][0] = front[a][0]
        sww = back[d][2]
        back[d][2] = sw
        sw = bot[d][2]
        bot[d][2] = sww
        d -= 1
        front[a][0] = sw
    sw = left[2][0]
    left[2][0] = left[0][0]
    sww = left[2][2]
    left[2][2] = sw
    sw = left[0][2]
    left[0][2] = sww
    left[0][0] = sw

    sw = left[1][0]
    left[1][0] = left[0][1]
    sww = left[2][1]
    left[2][1] = sw
    sw = left[1][2]
    left[1][2] = sww
    left[0][1] = sw
    print("L' Done")


def turn_right():
    moves.append("T")
    sw = ''
    sww = ''
    for a in range(3):
        for b in range(3):
            sw = right[a][b]
            right[a][b] = front[a][b]
            sww = back[a][b]
            back[a][b] = sw
            sw = left[a][b]
            left[a][b] = sww
            front[a][b] = sw
    sw = top[2][0]
    top[2][0] = top[0][0]
    sww = top[2][2]
    top[2][2] = sw
    sw = top[0][2]
    top[0][2] = sww
    top[0][0] = sw

    sw = top[1][0]
    top[1][0] = top[0][1]
    sww = top[2][1]
    top[2][1] = sw
    sw = top[1][2]
    top[1][2] = sww
    top[0][1] = sw

    sw = bot[0][2]
    bot[0][2] = bot[0][0]
    sww = bot[2][2]
    bot[2][2] = sw
    sw = bot[2][0]
    bot[2][0] = sww
    bot[0][0] = sw

    sw = bot[1][2]
    bot[1][2] = bot[0][1]
    sww = bot[2][1]
    bot[2][1] = sw
    sw = bot[1][0]
    bot[1][0] = sww
    bot[0][1] = sw
    print("Turned right")


def turn_left():
    moves.append("t")
    sw = ''
    sww = ''
    for a in range(3):
        for b in range(3):
            sw = left[a][b]
            left[a][b] = front[a][b]
            sww = back[a][b]
            back[a][b] = sw
            sw = right[a][b]
            right[a][b] = sww
            front[a][b] = sw
    sw = bot[2][0]
    bot[2][0] = bot[0][0]
    sww = bot[2][2]
    bot[2][2] = sw
    sw = bot[0][2]
    bot[0][2] = sww
    bot[0][0] = sw

    sw = bot[1][0]
    bot[1][0] = bot[0][1]
    sww = bot[2][1]
    bot[2][1] = sw
    sw = bot[1][2]
    bot[1][2] = sww
    bot[0][1] = sw

    sw = top[0][2]
    top[0][2] = top[0][0]
    sww = top[2][2]
    top[2][2] = sw
    sw = top[2][0]
    top[2][0] = sww
    top[0][0] = sw

    sw = top[1][2]
    top[1][2] = top[0][1]
    sww = top[2][1]
    top[2][1] = sw
    sw = top[1][0]
    top[1][0] = sww
    top[0][1] = sw
    print("Turned Left")


def fp():
    moves.append("f")
    sw = ' '
    sww = ' '
    for x in range(3):
        d = 2
        for a in range(3):
            sw = left[a][2]
            left[a][2] = bot[2][d]
            sww = top[2][d]
            top[2][d] = sw
            sw = right[d][0]
            right[d][0] = sww
            bot[2][d] = sw
            d -= 1
        sw = front[0][2]
        front[0][2] = front[0][0]
        sww = front[2][2]
        front[2][2] = sw
        sw = front[2][0]
        front[2][0] = sww
        front[0][0] = sw

        sw = front[1][2]
        front[1][2] = front[0][1]
        sww = front[2][1]
        front[2][1] = sw
        sw = front[1][0]
        front[1][0] = sww
        front[0][1] = sw
    print("F' Done")


def f():
    moves.append("F")
    sw = ' '
    sww = ' '
    d = 2
    for a in range(3):
        sw = left[a][2]
        left[a][2]=bot[2][d]
        sww = top[2][d]
        top[2][d] = sw
        sw = right[d][0]
        right[d][0] = sww
        bot[2][d] = sw
        d-=1
    sw = front[0][2]
    front[0][2] = front[0][0]
    sww = front[2][2]
    front[2][2] = sw
    sw = front[2][0]
    front[2][0] = sww
    front[0][0] = sw

    sw = front[1][2]
    front[1][2] = front[0][1]
    sww = front[2][1]
    front[2][1] = sw
    sw = front[1][0]
    front[1][0] = sww
    front[0][1] = sw
    print("F Done")


def righty():
    moves.append("Z")
    r()
    u()
    rp()
    up()
    print("Righty Done")


def lefty():
    moves.append("z")
    lp()
    up()
    l()
    u()
    print("Lefty Done")


def d():
    moves.append("D")
    sw = ''
    sww = ''
    for a in range(3):
        sw = right[2][a]
        right[2][a] = front[2][a]
        sww = back[2][a]
        back[2][a] = sw
        sw = left[2][a]
        left[2][a] = sww
        front[2][a] = sw
    sw = bot[0][2]
    bot[0][2] = bot[0][0]
    sww = bot[2][2]
    bot[2][2] = sw
    sw = bot[2][0]
    bot[2][0] = sww
    bot[0][0] = sw

    sw = bot[1][2]
    bot[1][2] = bot[0][1]
    sww = bot[2][1]
    bot[2][1] = sw
    sw = bot[1][0]
    bot[1][0] = sww
    bot[0][1] = sw
    print("D done")


def dp():
    moves.append("d")
    sw = ''
    sww = ''
    for a in range(3):
        sw = left[2][a]
        left[2][a] = front[2][a]
        sww = back[2][a]
        back[2][a] = sw
        sw = right[2][a]
        right[2][a] = sww
        front[2][a] = sw
    sw = bot[2][0]
    bot[2][0] = bot[0][0]
    sww = bot[2][2]
    bot[2][2] = sw
    sw = bot[0][2]
    bot[0][2] = sww
    bot[0][0] = sw

    sw = bot[1][0]
    bot[1][0] = bot[0][1]
    sww = bot[2][1]
    bot[2][1] = sw
    sw = bot[1][2]
    bot[1][2] = sww
    bot[0][1] = sw
    print("D' Done")


def cross_c():
    if bot[1][0] == 'w' and bot[0][1] == 'w' and bot[1][2] == 'w' and bot[2][1] == 'w':
        return False
    return True


def cross_sol():
    print("==========================Solving Started=======================")
    cnt=0
    while cross_c():
        cnt+=1
        if top[2][1] == 'w':
            if front[0][1] == front[1][1]:
                f()
                if front[1][0] == 'w':
                    ch = left[1][2]
                    if ch == left[1][1]:
                        l()
                elif front[1][2] == 'w':
                    ch = right[1][0]
                    if ch == right[1][1]:
                        rp()
                f()
                if front[1][0] == 'w':
                    ch = left[1][2]
                    if ch == left[1][1]:
                        l()
                elif front[1][2] == 'w':
                    ch = right[1][0]
                    if ch == right[1][1]:
                        rp()
            elif front[0][1]==left[1][1]:
                u()
                l()
                l()
            elif front[0][1]==right[1][1]:
                up()
                rp()
                rp()
            elif front[0][1]==back[1][1]:
                u()
                u()
                turn_right()
                l()
                l()
                turn_left()
            else:
                up()
            continue
        elif front[1][0] == 'w':
            ch = left[1][2]
            if ch == left[1][1]:
                l()
            elif left[1][2]==front[1][1]:
                f()
            elif left[1][2]==right[1][1]:
                if bot[2][1] == 'w' and front[2][1] == front[1][1]:
                    f()
                    f()
                    rp()
                    f()
                    f()
                else:
                    f()
                    f()
                    rp()
            else:
                f()
                up()
                fp()
            continue

        elif front[1][2] == 'w':
            ch = right[1][0]
            if ch == right[1][1]:
                rp()
            elif right[1][0]==front[1][1]:
                fp()
            elif right[1][0]==left[1][1]:
                if bot[2][1] == 'w' and front[2][1] == front[1][1]:
                    f()
                    f()
                    l()
                    f()
                    f()
                else:
                    f()
                    f()
                    l()
            else:
                fp()
                u()
                f()
            continue

        elif front[0][1] == 'w':
            if top[2][1] == front[1][1]:
                x = 0
                if left[0][1] == 'w' and top[1][0]==left[1][1]:
                    l()
                    x = 1
                elif left[2][1] == 'w' and bot[1][2]==left[1][1]:
                    lp()
                    x = 1
                elif left[1][2] == 'w' and front[1][0]==left[1][1]:
                    x = 1
                elif left[1][0] == 'w' and back[1][2]==left[1][1]:
                    x = 1
                    l()
                    l()
                if right[0][1] == 'w' and top[1][2]==right[1][1]:
                    rp()
                    x = 2
                elif right[2][1] == 'w' and bot[1][0]==right[1][1]:
                    r()
                    x = 2
                elif right[1][0] == 'w' and front[1][2]==right[1][1]:
                    x = 2
                elif right[1][2] == 'w' and back[1][0]==right[1][1]:
                    x = 2
                    r()
                    r()
                if x == 0:
                    rp()
                    f()
                    r()
                    u()
                    f()
                    if front[1][0] == 'w':
                        ch = left[1][2]
                        if ch == left[1][1]:
                            l()
                    elif front[1][2] == 'w':
                        ch = right[1][0]
                        if ch == right[1][1]:
                            rp()
                    f()
                    if front[1][0] == 'w':
                        ch = left[1][2]
                        if ch == left[1][1]:
                            l()
                    elif front[1][2] == 'w':
                        ch = right[1][0]
                        if ch == right[1][1]:
                            rp()
                elif x == 1:
                    rp()
                    f()
                    r()
                    u()
                    f()
                    if front[1][0] == 'w':
                        ch = left[1][2]
                        if ch == left[1][1]:
                            l()
                    elif front[1][2] == 'w':
                        ch = right[1][0]
                        if ch == right[1][1]:
                            rp()
                    f()
                    if front[1][0] == 'w':
                        ch = left[1][2]
                        if ch == left[1][1]:
                            l()
                    elif front[1][2] == 'w':
                        ch = right[1][0]
                        if ch == right[1][1]:
                            rp()
                    l()
                    l()
                elif x == 2:
                    l()
                    fp()
                    lp()
                    up()
                    f()
                    if front[1][0] == 'w':
                        ch = left[1][2]
                        if ch == left[1][1]:
                            l()
                    elif front[1][2] == 'w':
                        ch = right[1][0]
                        if ch == right[1][1]:
                            rp()
                    f()
                    if front[1][0] == 'w':
                        ch = left[1][2]
                        if ch == left[1][1]:
                            l()
                    elif front[1][2] == 'w':
                        ch = right[1][0]
                        if ch == right[1][1]:
                            rp()
                    rp()
                    rp()
            elif top[2][1]==left[1][1]:
                fp()
                l()
            elif top[2][1]==right[1][1]:
                f()
                rp()
            else:
                up()
                up()
                turn_left()
                turn_left()
            continue

        elif front[2][1] == 'w':
            f()
            if front[1][0] == 'w':
                ch = left[1][2]
                if ch == left[1][1]:
                    l()
            elif front[1][2] == 'w':
                ch = right[1][0]
                if ch == right[1][1]:
                    rp()
            f()
            if front[1][0] == 'w':
                ch = left[1][2]
                if ch == left[1][1]:
                    l()
            elif front[1][2] == 'w':
                ch = right[1][0]
                if ch == right[1][1]:
                    rp()
            if top[2][1] == front[1][1]:
                x = 0
                if left[0][1] == 'w' and top[1][0] == left[1][1]:
                    l()
                    x = 1
                elif left[2][1] == 'w' and bot[1][2] == left[1][1]:
                    lp()
                    x = 1
                elif left[1][2] == 'w' and front[1][0] == left[1][1]:
                    x = 1
                elif left[1][0] == 'w' and back[1][2] == left[1][1]:
                    x = 1
                    l()
                    l()
                if right[0][1] == 'w' and top[1][2] == right[1][1]:
                    rp()
                    x = 2
                elif right[2][1] == 'w' and bot[1][0] == right[1][1]:
                    r()
                    x = 2
                elif right[1][0] == 'w' and front[1][2] == right[1][1]:
                    x = 2
                elif right[1][2] == 'w' and back[1][0] == right[1][1]:
                    x = 2
                    r()
                    r()
                if x == 0:
                    rp()
                    f()
                    r()
                    u()
                    f()
                    if front[1][0] == 'w':
                        ch = left[1][2]
                        if ch == left[1][1]:
                            l()
                    elif front[1][2] == 'w':
                        ch = right[1][0]
                        if ch == right[1][1]:
                            rp()
                    f()
                    if front[1][0] == 'w':
                        ch = left[1][2]
                        if ch == left[1][1]:
                            l()
                    elif front[1][2] == 'w':
                        ch = right[1][0]
                        if ch == right[1][1]:
                            rp()
                elif x == 1:
                    rp()
                    f()
                    r()
                    u()
                    f()
                    if front[1][0] == 'w':
                        ch = left[1][2]
                        if ch == left[1][1]:
                            l()
                    elif front[1][2] == 'w':
                        ch = right[1][0]
                        if ch == right[1][1]:
                            rp()
                    f()
                    if front[1][0] == 'w':
                        ch = left[1][2]
                        if ch == left[1][1]:
                            l()
                    elif front[1][2] == 'w':
                        ch = right[1][0]
                        if ch == right[1][1]:
                            rp()
                    l()
                    l()
                elif x == 2:
                    l()
                    fp()
                    lp()
                    up()
                    f()
                    if front[1][0] == 'w':
                        ch = left[1][2]
                        if ch == left[1][1]:
                            l()
                    elif front[1][2] == 'w':
                        ch = right[1][0]
                        if ch == right[1][1]:
                            rp()
                    f()
                    if front[1][0] == 'w':
                        ch = left[1][2]
                        if ch == left[1][1]:
                            l()
                    elif front[1][2] == 'w':
                        ch = right[1][0]
                        if ch == right[1][1]:
                            rp()
                    rp()
                    rp()
            elif top[2][1] == left[1][1]:
                fp()
                l()
            elif top[2][1] == right[1][1]:
                f()
                rp()
            else:
                up()
                up()
                turn_left()
                turn_left()
            continue


        elif bot[2][1] == 'w' and front[2][1] != front[1][1]:
            f()
            if front[1][0] == 'w':
                ch = left[1][2]
                if ch == left[1][1]:
                    l()
            elif front[1][2] == 'w':
                ch = right[1][0]
                if ch == right[1][1]:
                    rp()
            f()
            if front[1][0] == 'w':
                ch = left[1][2]
                if ch == left[1][1]:
                    l()
            elif front[1][2] == 'w':
                ch = right[1][0]
                if ch == right[1][1]:
                    rp()
            up()
            continue
        turn_left()
        if cnt>50:
            return True
    return False
    print("=========Cross Done==========")

def wipe():
    for i in range(len(moves)-1,-1,-1):
        moves.pop(i)

def step_cnt(set):
    cnt=0
    for ch in set:
        if ch not in "Tt":
            if ch in "Zz":
                cnt+=4
            else:
                cnt+=1
    # print("Number of Steps:",end=" ")
    # print(cnt)
    return cnt

def rand_suff():
    import random
    for i in range(50):
        a=random.randint(0,12)
        if a==0:
            r()
        elif a==1:
            rp()
        elif a==2:
            l()
        elif a==3:
            lp()
        elif a==4:
            u()
        elif a==5:
            up()
        elif a==6:
            turn_left()
        elif a==7:
            turn_right()
        elif a==8:
            d()
        elif a==9:
            dp()
        elif a==10:
            righty()
        elif a==11:
            lefty()

def ccc(a):
    c1,c2=0,0
    for i in lis:
        if i<(a+2):
            c1+=1
        else:
            c2+=1
    print(c1,'  ',c2)

def optimize(move):
    mov_n = []
    n = len(move)
    a = 0
    m = " "
    while a < n:
        m = str(move[a])
        if m not in "Zz":
            if (a + 3) < n:
                if move[a] == str(move[a + 1]) and move[a] == str(move[a + 2]) and move[a] == str(move[a + 3]):
                    a += 4
                    continue
            if (a + 2) < n:
                if move[a] == str(move[a + 1]) and move[a] == str(move[a + 2]):
                    if m.islower():
                        m = m.upper()
                        mov_n.append(m)
                        a += 3
                        continue
                    else:
                        mov_n.append(m.lower())
                        a += 3
                        continue
            if (a + 1) < n:
                if m == str(move[a + 1]):
                    mov_n.append(m.upper() + "2")
                    a += 2
                    continue
                elif m.isupper():
                    if m.lower() == move[a + 1]:
                        a += 2
                        continue
                else:
                    if m.upper() == str(move[a + 1]):
                        a += 2
                        continue
            mov_n.append(m)
            a += 1
    return mov_n

def con(set):
    new=[]
    for i in set:
        m=str(i)
        if m=="Y":
            new.append("t")
        elif m=="Y'":
            new.append("T")
        elif m=="R":
            new.append("R")
        elif m=="R'":
            new.append("r")
        elif m=="L":
            new.append("L")
        elif m=="L'":
            new.append("l")
        elif m=="U":
            new.append("U")
        elif m=="U'":
            new.append("u")
        elif m=="D":
            new.append("D")
        elif m=="D'":
            new.append("d")
        elif m=="F":
            new.append("F")
        elif m=="F'":
            new.append("f")
        else:
            new.append(m)
    return new


def form():
    cube=''
    for a in top:
        for b in a:
            cube+=str(b)
    for a in left:
        for b in a:
            cube+=str(b)
    for a in front:
        for b in a:
            cube+=str(b)
    for a in right:
        for b in a:
            cube+=str(b)
    for a in back:
        for b in a:
            cube+=str(b)
    for a in bot[::-1]:
        for b in a[::-1]:
            cube+=str(b)
    return cube

# r()
# u()
# f()
# rand_suff()
# import Kociemba as k
# for a in range(4):
#     if front[1][1]=='r':
#         break
#     turn_right()
# print(k.kocem(form()))
# print(len(k.kocem(form())))

# from time import sleep
lis=[]
# tot=0
# fail=0
# for i in range(1000):
#     rand_suff()
#     wipe()
#     # sleep(3)
#     check=cross_sol()
#     if check:
#         fail+=1
#         continue
#     # chap()
#     moves=optimize(moves)
#     lis.append(step_cnt())
#     tot+=step_cnt()
#     # print(moves)
# print()
# print(tot/(1000-fail))
# ccc(tot/(1000-fail))
# print(max(lis),"  ",min(lis))
# print(fail)
'''
import Kociemba as k
tot=0
tot2=0
tot3=0
for x in range(20):
    rand_suff()
    for a in range(4):
        if front[1][1]=='r':
            break
        turn_right()
    cub=form()
    print(x)
    m_koc=k.kocem(cub)
    m_beg = k.begin(cub)
    m_cfop=k.cfop_new(cub)
    m_koc=optimize(con(m_koc))
    m_cfop=optimize(con(m_cfop))
    m_beg=optimize(con(m_beg))
    tot+=step_cnt(m_koc)
    tot2+=step_cnt(m_cfop)
    tot3+=step_cnt(m_beg)
    print("!!!!!!!!!!!!!@@@@@@@@@@@@@@@@@@@")
print("Koceimba= ",tot/20)
print("CFOP= ",tot2/20)
print("Beginner= ",tot3/20)
'''
def ran_k(b):
    import Kociemba as k
    rand_suff()
    for a in range(4):
        if front[1][1]=='r':
            break
        turn_right()
    cub=form()
    m_koc=k.kocem(cub)
    m_koc=optimize(con(m_koc))
    s=""
    for a in m_koc:
        s+=a+" "
    return str(s)
