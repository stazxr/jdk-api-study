import java.nio.CharBuffer;
import java.util.Arrays;

/**
 * CharBuffer
 */
public class CharBufferDemo {
    public static void main(String[] args) {
        /* ***************************** 实例化方法开始 ***************************** */

        // allocate: 用于分配指定容量的字符缓冲区
        // 它的原理是在内存中分配一块连续的存储空间来存储字符数据，并返回一个新的 CharBuffer 实例，该实例封装了这块存储空间
        // 1、计算所需存储空间的大小：根据传入的容量参数，计算出所需的存储空间大小。通常情况下，存储空间的大小会略大于指定的容量，以满足内部管理和优化的需要。
        // 2、分配存储空间：在内存中分配一块连续的存储空间，用于存储字符数据。这块存储空间通常是通过 Java 虚拟机的堆内存来分配的，但具体的分配方式可能因 JVM 实现而异。
        // 3、创建 CharBuffer 实例：创建一个新的 CharBuffer 实例，并将分配的存储空间封装在这个实例中。该实例包含了一些重要的属性，如容量、位置、限制等，以便后续对缓冲区的操作。
        // 4、返回实例：将创建的 CharBuffer 实例返回给调用者，使其可以通过该实例来操作分配的存储空间。
        CharBuffer buffer1 = CharBuffer.allocate(10);

        // 将指定的字符数组包装为一个新的 CharBuffer 缓冲区，实现为 HeapCharBuffer
        // mark  -  position  -  limit  -  capacity  -  offset
        //  -1  -  0  -  字符数组长度  -  字符数组长度  -  0
        char[] wrapAry1 = {'a', 'b', 'c', 'd', 'e'};
        CharBuffer buffer2 = CharBuffer.wrap(wrapAry1);

        // 将指定的字符数组包装为一个新的 CharBuffer 缓冲区，实现为 HeapCharBuffer
        // mark  -  position  -  limit  -  capacity  -  offset
        //  -1  -  offset 参数值  -  offset 参数值 + length 参数值  -  字符数组长度  -  0
        char[] wrapAry2 = {'a', 'b', 'c', 'd', 'e'};
        CharBuffer buffer3 = CharBuffer.wrap(wrapAry2, 1, 3);

        // 将指定的 CharSequence 对象（如 String）包装为一个新的 CharBuffer 缓冲区，实现为 StringCharBuffer
        // mark  -  position  -  limit  -  capacity  -  offset
        //  -1  -  0  -  字符串长度  -  字符串长度  -  0
        String wrapStr1 = "hello";
        CharBuffer buffer4 = CharBuffer.wrap(wrapStr1);

        // 将指定的 CharSequence 对象（如 String）包装为一个新的 CharBuffer 缓冲区，实现为 StringCharBuffer
        // mark  -  position  -  limit  -  capacity  -  offset
        //  -1  -  start 参数值  -  start 参数值 + (end 参数值 - start 参数值)  -  字符数组长度  -  0
        String wrapStr2 = "hello";
        CharBuffer buffer5 = CharBuffer.wrap(wrapStr2, 1, 1);

        /* ***************************** 实例化方法结束 ***************************** */

        /* ***************************** 属性说明开始 ***************************** */

        // 属性1：容量，缓冲区的容量是指缓冲区所能容纳的字符数量。它是在创建缓冲区时指定的，并且不能改变。
        // 容量决定了缓冲区的大小，即可以存储多少个字符。
        int capacity = buffer1.capacity();
        System.out.println("capacity: " + capacity);

        // 属性2：限制，缓冲区的限制是指缓冲区当前可读取或写入的字符数量的上限。
        // 在读取模式下，限制通常等于缓冲区中有效数据的末尾位置；
        // 在写入模式下，限制通常等于缓冲区的容量。
        // 限制可以随时修改，以控制对缓冲区的读写操作。
        int limit = buffer1.limit();
        System.out.println("limit: " + limit);

        // 属性3：位置，缓冲区的位置是指下一个读取或写入操作将从缓冲区的哪个位置开始。
        // 初始时，位置通常为零，表示从缓冲区的开头开始进行操作。
        // 位置可以随着读写操作的进行而递增，但不能超过限制。
        int position = buffer1.position();
        System.out.println("position: " + position);

        // 属性4：标记，缓存区的标记是一个临时的位置标记，用于暂存某个位置的状态。
        // 可以通过 mark() 方法设置标记，并在需要时通过 reset() 方法恢复到标记的位置。
        // 标记可以随时清除，以释放资源。
        // buffer1.mark

        // 属性5：字符数组，用于存储缓冲区中的字符数据，是内部属性，通常不直接对其进行操作。
        // buffer1.hb
        char[] hb = buffer1.array();
        System.out.println("array: " + Arrays.toString(hb));

        // 属性6：字符数组的偏移量。
        int offset = buffer1.arrayOffset();
        System.out.println("offset: " + offset);

        // 属性7：用于表示缓冲区是否为只读的。
        // 当 isReadOnly 的值为 true 时，表示该缓冲区为只读的，不允许对其进行写入操作；
        // 当 isReadOnly 的值为 false 时，表示该缓冲区可读可写。
        // 保护数据不被修改： 如果一个缓冲区被标记为只读，那么任何试图对其进行写入操作的尝试都会导致 ReadOnlyBufferException 异常。这样可以确保缓冲区中的数据不会在不应该被修改的情况下被修改。
        // 提高安全性：将缓冲区标记为只读可以提高程序的安全性，特别是在多线程环境中。只读缓冲区可以被多个线程同时访问，而不必担心数据被意外修改。
        // 节省内存： 将缓冲区标记为只读可以节省内存空间。在某些情况下，如果确定某个缓冲区的数据不需要被修改，可以将其标记为只读，这样可以节省一些内存空间。
        boolean readOnly = buffer1.isReadOnly();
        System.out.println("readOnly: " + readOnly);

        // 属性8：
        // buffer1.address

        // 规则: mark <= position <= limit <= capacity

        /* ***************************** 属性说明结束 ***************************** */

        /* ***************************** 属性说明开始 ***************************** */

        /* ***************************** 属性说明结束 ***************************** */

        /* ***************************** 属性说明开始 ***************************** */

        /* ***************************** 属性说明结束 ***************************** */




        // buffer1 与 buffer2 是同一个对象
//        Buffer buffer2 = buffer1.mark();
//
//        buffer2.reset();
//
//        buffer2.limit(1);
    }
}
