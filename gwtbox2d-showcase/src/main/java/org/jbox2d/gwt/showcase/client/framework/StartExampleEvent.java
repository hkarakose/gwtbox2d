package org.jbox2d.gwt.showcase.client.framework;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class StartExampleEvent extends GwtEvent<StartExampleEvent.Handler>{
  
  public static Type<Handler> TYPE = new Type<StartExampleEvent.Handler>();
  
  private BaseExample example;
  
  public StartExampleEvent(BaseExample example) {
    super();
    this.example = example;
  }

  public static interface Handler extends EventHandler{
    void onStartExample(BaseExample example);
  }

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(Handler handler) {
    handler.onStartExample(example);
  }
}
