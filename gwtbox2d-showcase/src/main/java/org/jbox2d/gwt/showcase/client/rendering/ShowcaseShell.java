package org.jbox2d.gwt.showcase.client.rendering;

import org.jbox2d.gwt.showcase.client.framework.BaseExample;
import org.jbox2d.gwt.showcase.client.framework.StartExampleEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ShowcaseShell extends Composite {

  interface ShowcaseShellUiBinder extends UiBinder<Widget, ShowcaseShell> {}

  private static ShowcaseShellUiBinder uiBinder = GWT.create(ShowcaseShellUiBinder.class);
  
  private EventBus eventBus;
  private ExamplesTreeModel treeViewModel;

  @UiField
  SimplePanel contentPanel;
  
  @UiField
  Button restartButton;

  /**
   * The main menu used to navigate to examples.
   */
  @UiField(provided = true)
  CellTree mainMenu;

  /**
   * The current {@link ContentWidget} being displayed.
   */
  private ExampleView exampleView;

  /**
   * Construct the {@link ShowcaseShell}.
   * 
   * @param treeModel
   *          the treeModel that backs the main menu
   */
  public ShowcaseShell(EventBus eventBus) {
    this.eventBus = eventBus;
    
    // Create the cell tree.
    treeViewModel = ExamplesTreeModel.create(eventBus);
    mainMenu = new CellTree(treeViewModel, null);
    mainMenu.setAnimationEnabled(true);
    mainMenu.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);

    // Initialize the ui binder.
    initWidget(uiBinder.createAndBindUi(this));

    exampleView = new ExampleView(eventBus);
    contentPanel.add(exampleView);

    // select first example
    TreeNode firstCategoryNode = mainMenu.getRootTreeNode().setChildOpen(0, true);
    treeViewModel.getSelectionModel().setSelected((BaseExample) firstCategoryNode.getChildValue(0), true);
  }

  public CanvasDebugDraw getCameraRenderer() {
    return exampleView.getCameraRenderer();
  }
  
  @UiHandler("restartButton")
  public void onRestartButtonClick(ClickEvent click){
    BaseExample be = treeViewModel.getSelectionModel().getSelectedObject();
    if(be != null){
      eventBus.fireEvent(new StartExampleEvent(be));
    }
  }
}
