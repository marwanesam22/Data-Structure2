import random
import os

number_of_element = 5000000
dir_name = "in5000000"

os.mkdir(dir_name)

file_name = os.path.join(dir_name, dir_name + "_average.txt")
arr = [random.randint(1, 1000000) for i in range(number_of_element)]
string_array = [str(num) for num in arr]
result = ",".join(string_array)
with open(file_name, "w") as f:
    f.write(result)


file_name = os.path.join(dir_name, dir_name + "_best.txt")
arr_sorted = sorted(arr)
string_array = [str(num) for num in arr_sorted]
result = ",".join(string_array)
with open(file_name, "w") as f:
    f.write(str(result))

file_name = os.path.join(dir_name, dir_name + "_worst.txt")
arr_sorted_reverse = sorted(arr, reverse=True)
string_array = [str(num) for num in arr_sorted_reverse]
result = ",".join(string_array)
with open(file_name, "w") as f:
    f.write(result)