package jelsos.lib.ex;

public sealed
interface Err
permits Err.Message,Err.Failure
{

  record Message(String text) implements Err {
  }

  record Failure(RuntimeException ex) implements Err {
  }

}
