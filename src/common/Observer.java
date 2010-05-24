package common;

/**
 * Observer interface used for the Observer pattern
 * @author Ian Graves
 *
 */
public interface Observer<T>
{
    public void alert(T obj);
}
