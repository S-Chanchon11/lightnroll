import sys
import soundfile as sf
from os.path import dirname, join
import numpy as np
from math import log2
import json

def pcp(y,sr):
    fref = 261.63
    fft_val = np.fft.fft(y)
    N = len(fft_val)
    def M(l, fs, fref):
        if l == 0:
            return -1
        return round(12 * log2((fs * l) / (N * fref))) % 12

    pcp = [0 for p in range(12)]
    for p in range(12):
        for l in range((N // 2) - 1):
            temp = M(l, fs=sr, fref=fref)

            if p == temp:
                h = abs(fft_val[l]) ** 2
                pcp[p] += h

    pcp_norm = [0 for p in range(12)]
    for p in range(12):
        pcp_norm[p] = pcp[p] / sum(pcp)

    return list(pcp_norm)
    # return tmp
def extract(file):

    path = join(dirname(__file__),file)

    y, sr = sf.read(path)

    result = pcp(y,sr)

    return result
