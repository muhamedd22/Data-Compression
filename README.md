# Data_Compression

Why we need to compress data : 

● To reduce the volume of data to be transmitted (text, fax, images).

● To reduce storage requirements (speech, audio, video). 

● To reduce the bandwidth required for transmission.

the techniques used in this repository is : 

● LZ77 : achieve compression by replacing repeated occurrences of data with references to a single copy of that data existing earlier in the uncompressed data stream. A match is encoded by a pair of numbers called a length-distance pair, which is equivalent to the statement "each of the next length characters is equal to the characters exactly distance characters behind it in the uncompressed stream". (The distance is sometimes called the offset instead.).


● LZ78 : chieve compression by replacing repeated occurrences of data with references to a dictionary that is built based on the input data stream. Each dictionary entry is of the form dictionary[...] = {index, character}, where index is the index to a previous dictionary entry, and character is appended to the string represented by dictionary[index]


● LZW : is an LZ78-based algorithm that uses a dictionary pre-initialized with all possible characters (symbols) or emulation of a pre-initialized dictionary.


● Vector Quantization : is a classical quantization technique from signal processing that allows the modeling of probability density functions by the distribution of prototype vectors. It was originally used for data compression. It works by dividing a large set of points (vectors) into groups having approximately the same number of points closest to them. Each group is represented by its centroid point, as in k-means and some other clustering algorithms.


● Adaptive Huffman : is an adaptive coding technique based on Huffman coding. It permits building the code as the symbols are being transmitted, having no initial knowledge of source distribution, that allows one-pass encoding and adaptation to changing conditions in data.


● Arithmetic Coding : Arithmetic coding completely by passes the idea of replacing an input symbol with a specific code. Instead, it takes a stream of input symbols and replaces it with a single  floating point number The longer and more complex the message, the more bits are needed to  represents the output number.

