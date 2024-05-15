from math import log2
import numpy as np
from scipy.io import wavfile
from pathlib import Path
from os.path import dirname, join
import os

# def tuner():
#     np.set_printoptions(threshold=np.inf)
#     # Load the audio file
#     # y, sr = librosa.load(file)
#     y = [-1.17742792e-02 -1.22950487e-02 -1.19019710e-02 -1.19186165e-02
#          -1.17984954e-02 -1.18791312e-02 -1.22266710e-02 -1.21622849e-02
#          -1.20120514e-02 -1.22976545e-02 -1.22284219e-02 -1.24561554e-02
#          -1.28600020e-02 -1.27080232e-02 -1.35590928e-02 -1.44243529e-02
#          -1.44213513e-02 -1.40170623e-02 -1.47990268e-02 -1.44738220e-02
#          -1.47614554e-02 -1.54396323e-02 -1.59481466e-02 -1.53815206e-02
#          -1.67465433e-02 -1.62356514e-02 -1.77106000e-02 -1.70512721e-02]
#     sr = 22050
#     # Calculate the pitch
#     pitch, magnitudes = librosa.piptrack(y=y, sr=sr)
#
#     # Get the index of maximum value in pitch array
#     pitch_index = np.argmax(pitch, axis=0)
#
#     # Convert the index to pitch values
#     frequencies = librosa.fft_frequencies(sr=sr)
#     pitch_values = frequencies[pitch_index]
#
#     return pitch_values

def tuner(wav_fname):

    samplerate, data = wavfile.read(wav_fname)

    return samplerate

# ALTERNATIVE CHOICE

# Detection = aubio.pitch("yin", 2048, 2048//2, sr)

# # Empty list to store pitch values
# pitch_values = []

# # Iterate through audio frames and compute pitch
# for frame in range(0, len(y), 2048):
#     pitch = pDetection(y[frame:frame+2048])[0]
#     pitch_values.append(pitch)

def pitch_class_profile(audio_path):
    y=1
    sr=2
    # y, sr = librosa.load(audio_path)
    fft_val = np.fft.fft(y)

    N = len(fft_val)

    def M(l, fs, fref):
        if l == 0:
            return -1
        return round(12 * log2((fs * l) / (N * fref))) % 12

    pcp = [0 for p in range(12)]
    for p in range(12):
        for l in range((N // 2) - 1):

            temp = M(l, fs=sr, fref=261.63)

            if p == temp:  # p = 0...11

                h = abs(fft_val[l]) ** 2

                pcp[p] += h

    pcp_norm = [0 for p in range(12)]
    for p in range(12):
        pcp_norm[p] = pcp[p] / sum(pcp)

    return list(pcp_norm)
def get_pwd():

    # current_working_directory = Path.cwd()
    current_working_directory = os.getcwd()
    return current_working_directory

filename = join(dirname(__file__), "A_YAMAHA_1.wav")
# filename = dirname(__file__)


# wav_fname = "/Users/snow/lightnroll/app/src/main/python/A_YAMAHA_1.wav"
# sr,data = tuner(wav_fname)
# test = 11
# file = ''
# tuner()