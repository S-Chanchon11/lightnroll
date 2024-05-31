# from aubio import source, onset
#
# def get_onset_times(file_path):
#     window_size = 1024 # FFT size
#     hop_size = window_size // 4
#
#     sample_rate = 0
#     src_func = source(file_path, sample_rate, hop_size)
#     sample_rate = src_func.samplerate
#     onset_func = onset('default', window_size, hop_size)
#
#     duration = float(src_func.duration) / src_func.samplerate
#
#     onset_times = [] # seconds
#     while True: # read frames
#         samples, num_frames_read = src_func()
#         if onset_func(samples):
#             onset_time = onset_func.get_last_s()
#             if onset_time < duration:
#                 onset_times.append(onset_time)
#             else:
#                 break
#         if num_frames_read < hop_size:
#             break
#
#     return onset_times
#
# def main():
#     file_path = '../game/audio/my-music.mp3'
#     onset_times = get_onset_times(file_path)
#     # remove extension, .mp3, .wav etc.
#     file_name_no_extension, _ = os.path.splitext(file_path)
#     output_name = file_name_no_extension + '.beatmap.txt'
#     with open(output_name, 'wt') as f:
#         f.write('\n'.join(['%.4f' % onset_time for onset_time in onset_times]))
import sys
import soundfile as sf
from os.path import dirname, join
import numpy as np
def extract(file):

    # y, sr = sf.read(path)
    path = join(dirname(__file__),file)
    np.set_printoptions(threshold=sys.maxsize)
    # with open(path,'r') as of:
    y, sr = sf.read(path)
    # array = []
    # array.append(y)
    # array.append(sr)
    return sr

    # return "extract"
# extract("app/src/main/res/raw/out_0.wav")