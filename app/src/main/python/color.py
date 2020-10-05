import cv2
import numpy
import matplotlib.pyplot as plt

def green(h,i):
    low_g=numpy.array([44,175,150])
    high_g=numpy.array([73,255,255])
    m = cv2.inRange(h, low_g, high_g)
    r = cv2.bitwise_and(i, i, mask=m)
    return m,r

def blue(h,i):
    low_b=numpy.array([95,220,170])
    high_b=numpy.array([103,255,255])
    m = cv2.inRange(h, low_b, high_b)
    r = cv2.bitwise_and(i, i, mask=m)
    return m,r

def red(h,i):
    low_r=numpy.array([0,175,200])
    high_r=numpy.array([10,255,255])
    low_r1=numpy.array([170,170,0])
    high_r1=numpy.array([255,255,255])
    m = cv2.inRange(h, low_r, high_r)
    m1 = cv2.inRange(h, low_r1, high_r1)
    m=cv2.add(m, m1)
    r = cv2.bitwise_and(i, i, mask=m)
    return m,r

def org(h,i):
    low_o=numpy.array([12,180,216])
    high_o=numpy.array([21,255,255])
    m = cv2.inRange(h, low_o, high_o)
    r = cv2.bitwise_and(i, i, mask=m)
    return m,r

def yellow(h,i):
    low_y=numpy.array([27,160,170])
    high_y=numpy.array([34,255,255])
    m = cv2.inRange(h, low_y, high_y)
    r = cv2.bitwise_and(i, i, mask=m)
    return m,r

def white(h,i):
    low_w=numpy.array([0,12,217])
    high_w=numpy.array([255,50,255])
    m = cv2.inRange(h, low_w, high_w)
    r = cv2.bitwise_and(i, i, mask=m)
    return m,r

def rot(i):
    i=cv2.rotate(i,cv2.ROTATE_90_CLOCKWISE)
    return i

# img=cv2.imread('s2.jpg')
# im=cv2.cvtColor(img,cv2.COLOR_BGR2RGB)
# hs=cv2.cvtColor(im,cv2.COLOR_RGB2HSV)
#
# _,res=green(hs,im)
# _,r1=red(hs,im)
# _,r2=blue(hs,im)
# _,r3=org(hs,im)
# _,r5=yellow(hs,im)
#
# res=res+r1+r2+r3+r5
#
# plt.imshow(res)
# plt.show()