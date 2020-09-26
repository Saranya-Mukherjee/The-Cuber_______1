from rubik_solver import utils as u

def kocem(cube):
    return u.solve(cube, 'Kociemba')

def cfop_new(cube):
    return u.solve(cube, 'CFOP')

def begin(cube):
    return u.solve(cube, 'Beginner')