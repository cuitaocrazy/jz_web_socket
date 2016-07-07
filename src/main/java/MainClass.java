import java.net.URISyntaxException;


public class MainClass {

  public static void main(String[] args) throws URISyntaxException, InterruptedException {

    final WebSocketWrapper wrapper = new WebSocketWrapper("ws://60.205.93.81:9000/ws", "001", "USER", new WebSocketWrapper.Listener() {
      @Override
      public void onMessage(String message) {
        System.out.println(message);
      }

      @Override
      public void onError(Exception ex) {

      }

      @Override
      public void onClose() {

      }

      @Override
      public void onOpen() {

      }
    });

    wrapper.connectBlocking();
  }
}
