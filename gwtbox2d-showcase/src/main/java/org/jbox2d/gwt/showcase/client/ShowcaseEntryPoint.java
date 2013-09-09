package org.jbox2d.gwt.showcase.client;

import org.jbox2d.gwt.showcase.client.framework.ExampleRunner;
import org.jbox2d.gwt.showcase.client.rendering.ShowcaseShell;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class ShowcaseEntryPoint implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    EventBus eventBus = new SimpleEventBus();
    ShowcaseShell shell = new ShowcaseShell(eventBus);
    RootLayoutPanel.get().add(shell);
    final ExampleRunner exampleRunner = new ExampleRunner(eventBus, shell.getCameraRenderer());
    Event.addNativePreviewHandler(new NativePreviewHandler() {

      @Override
      public void onPreviewNativeEvent(NativePreviewEvent event) {
        if (event.getTypeInt() == Event.ONKEYPRESS) {
          NativeEvent nativeEvent = event.getNativeEvent();
          exampleRunner.onKeyPress((char) nativeEvent.getCharCode());
        } else if (event.getTypeInt() == Event.ONKEYDOWN) {
          NativeEvent nativeEvent = event.getNativeEvent();
          exampleRunner.onKeyDown(Character.toLowerCase((char) nativeEvent.getKeyCode()));
        } else if (event.getTypeInt() == Event.ONKEYUP) {
          NativeEvent nativeEvent = event.getNativeEvent();
          exampleRunner.onKeyUp(Character.toLowerCase((char) nativeEvent.getKeyCode()));
        }
      }
    });
  }
}
