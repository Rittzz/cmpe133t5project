package tournament;

/**
 * OBSERVER PATTERN HONKEY
 * @author Ian Graves
 *
 * @param <T> the class that will passed through the update method, not sure if this is right
 */
public interface Observer<T>
{
    public void update(T g);
}
