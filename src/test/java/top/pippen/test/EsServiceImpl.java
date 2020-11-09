package top.pippen.test;

/**
 * @author Pippen
 * @created 2020/11/06 20:35
 */
public class EsServiceImpl implements EsService {

    @Override
    public int sayHello(String toName){
        return toName.length();
    }
}
