package enumpro;

/**
 * Created by Administrator on 2016/12/16.
 */
public class enumTest {
    public enum Color {

        RED, GREEN, BLANK, YELLOW

    }

    static boolean isRed( Color color ){
        if ( Color.RED.equals( color )) {
            return true ;
        }
        return false ;
    }
    public static void main(String[] args) {
        System.out.println( isRed( Color.BLANK ) ) ;  //结果： false
        System.out.println( isRed( Color.RED ) ) ;    //结果： true
    }


}


