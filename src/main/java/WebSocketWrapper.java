import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;


public class WebSocketWrapper {
  private static final String CLIENT_SIGN_IN_FORMAT = "{\"eventType\":\"CLIENT_SIGN_IN\", \"id\":\"%s\", \"terminalType\":\"%s\"}";
  private final WebSocketClient ws;

  public WebSocketWrapper(final String url, final String id, final String terminalType, final Listener listener) throws URISyntaxException {

    this.ws = new WebSocketClient(new URI(url), new Draft_10()) {
      public void onOpen(ServerHandshake handshakedata) {
        listener.onOpen();
        this.send(String.format(CLIENT_SIGN_IN_FORMAT, id, terminalType));
      }

      public void onMessage(String message) {
        listener.onMessage(message);
      }

      public void onClose(int code, String reason, boolean remote) {
        listener.onClose();
      }

      public void onError(Exception ex) {
        listener.onError(ex);
      }
    };
  }

  public void send(String message) throws WebsocketNotConnectedException {
    ws.send(message);
  }

  public void connect() {
    ws.connect();
  }

  public void connectBlocking() throws InterruptedException {
    ws.connectBlocking();
  }

  public void close() {
    ws.close();
  }

  public interface Listener {
    void onMessage(String message);
    void onError(Exception ex);
    void onClose();
    void onOpen();
  }
}
