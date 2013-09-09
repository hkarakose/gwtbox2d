package org.jbox2d.gwt.showcase.client.rendering;

import java.util.List;

import org.jbox2d.gwt.showcase.client.framework.BaseExample;
import org.jbox2d.gwt.showcase.client.framework.ExampleMouseEvent;
import org.jbox2d.gwt.showcase.client.framework.StartExampleEvent;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ExampleView extends Composite implements StartExampleEvent.Handler, MouseMoveHandler,
    MouseDownHandler, MouseUpHandler {

  interface ExampleViewUiBinder extends UiBinder<Widget, ExampleView> {}

  private static ExampleViewUiBinder uiBinder = GWT.create(ExampleViewUiBinder.class);

  EventBus eventBus;
  @UiField
  SimplePanel canvasContainer;
  @UiField
  HTML instructions;
  CanvasDebugDraw cameraRenderer;

  public ExampleView(EventBus eventBus) {
    this.eventBus = eventBus;
    eventBus.addHandler(StartExampleEvent.TYPE, this);
    initWidget(uiBinder.createAndBindUi(this));
    cameraRenderer = new CanvasDebugDraw();
    Canvas canvas = cameraRenderer.getCanvas();
    canvasContainer.add(canvas);
    canvas.addMouseDownHandler(this);
    canvas.addMouseUpHandler(this);
    canvas.addMouseMoveHandler(this);
  }


  public CanvasDebugDraw getCameraRenderer() {
    return cameraRenderer;
  }

  @Override
  public void onStartExample(BaseExample example) {
    StringBuffer sb = new StringBuffer();
    List<String> instr = example.getInstructions();
    for (String instruaction : instr) {
      sb.append(instruaction).append("<br/>");
    }
    instructions.setHTML(sb.toString());
  }

  @Override
  public void onMouseUp(MouseUpEvent event) {
    event.preventDefault();
    ExampleMouseEvent eme = new ExampleMouseEvent(ExampleMouseEvent.MOUSE_EVENT_TYPE_UP,
        event.getX(), event.getY(), event.getNativeEvent().getShiftKey());
    eventBus.fireEvent(eme);
  }

  @Override
  public void onMouseDown(MouseDownEvent event) {
    event.preventDefault();
    ExampleMouseEvent eme = new ExampleMouseEvent(ExampleMouseEvent.MOUSE_EVENT_TYPE_DOWN,
        event.getX(), event.getY(), event.getNativeEvent().getShiftKey());
    eventBus.fireEvent(eme);
  }

  @Override
  public void onMouseMove(MouseMoveEvent event) {
    event.preventDefault();
    ExampleMouseEvent eme = new ExampleMouseEvent(ExampleMouseEvent.MOUSE_EVENT_TYPE_MOVE,
        event.getX(), event.getY(), event.getNativeEvent().getShiftKey());
    eventBus.fireEvent(eme);
  }
}
