package telsos.logging;

@FunctionalInterface
public interface LogFactory<T> {

  Log getLog(T arg);

}
