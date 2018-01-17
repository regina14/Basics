package DataStructure;
import java.util.*;

public class HeapRZ {
    private static final int DEFAULT_CAPACITY = 100;
    private int size;
    private Comparable[] array;

    public HeapRZ(){
        this(DEFAULT_CAPACITY);
    }

    public HeapRZ( int capacity )
    {
        size = 0;
        array = new Comparable[ capacity + 1 ];
    }

    public HeapRZ(Comparable[] items){
        size = items.length;
        array = new Comparable[(size + 2) * 11 / 10];
        int i = 1;
        for( Comparable item : items ){
            array[ i++ ] = item;
        }
        buildHeap();
    }

    //增
    public void insert(Comparable x){
        int hole = ++size;
        for (; hole > 1 && x.compareTo(array[hole/2]) < 0; hole /= 2){
            array[hole] = array[hole/2];
        }
        array[hole] = x;
    }

    //删
    public Comparable deleteMin( )
    {
        if( isEmpty( ) )
            return null;

        Comparable minItem = findMin( );
        array[ 1 ] = array[ size-- ];
        percolateDown( 1 );

        return minItem;
    }


    //查
    public Comparable findMin( )
    {
        if( isEmpty( ) )
            return null;
        return array[ 1 ];
    }



    //util

    public boolean isEmpty( )
    {
        return size == 0;
    }


    public boolean isFull( )
    {
        return size == array.length - 1;
    }

    private void buildHeap( )
    {
        for( int i = size / 2; i > 0; i-- )
            percolateDown( i );
    }

    private void percolateDown( int hole )
    {
        int child;
        Comparable tmp = array[ hole ];

        for( ; hole * 2 <= size; hole = child )
        {
            child = hole * 2;
            if( child != size &&
                    array[ child + 1 ].compareTo( array[ child ] ) < 0 )
                child++;
            if( array[ child ].compareTo( tmp ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }

    public static void main( String [ ] args )
    {
        int numItems = 50;
        HeapRZ h = new HeapRZ( numItems );
        int i = 37;

        try
        {
            for( i = 37; i != 0; i = ( i + 37 ) % numItems )
                h.insert( new Integer( i ) );
            System.out.println(Arrays.toString(h.array));
            System.out.println(h.findMin());
            h.deleteMin();
            System.out.println(Arrays.toString(h.array));
        }
        catch( Exception e )
        { System.out.println( "Overflow (expected)! " + i  ); }
    }
}
