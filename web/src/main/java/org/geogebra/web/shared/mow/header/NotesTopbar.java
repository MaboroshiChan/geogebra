package org.geogebra.web.shared.mow.header;

import java.util.ArrayList;
import java.util.List;

import org.geogebra.common.euclidian.CoordSystemListener;
import org.geogebra.common.euclidian.EuclidianConstants;
import org.geogebra.common.gui.AccessibilityGroup;
import org.geogebra.common.gui.SetLabels;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.gwtutil.NavigatorUtil;
import org.geogebra.web.full.css.MaterialDesignResources;
import org.geogebra.web.full.gui.toolbar.mow.toolbox.components.IconButton;
import org.geogebra.web.html5.css.GuiResourcesSimple;
import org.geogebra.web.html5.css.ZoomPanelResources;
import org.geogebra.web.html5.gui.zoompanel.ZoomPanel;
import org.geogebra.web.html5.main.AppW;
import org.geogebra.web.html5.util.AppletParameters;
import org.geogebra.web.resources.SVGResource;
import org.geogebra.web.shared.GlobalHeader;
import org.gwtproject.user.client.ui.FlowPanel;
import org.gwtproject.user.client.ui.SimplePanel;

public class NotesTopbar extends FlowPanel implements SetLabels, CoordSystemListener {
	private final AppletParameters appletParams;
	private TopbarController controller;
	private final List<IconButton> buttons = new ArrayList<>();
	private IconButton undoBtn;
	private IconButton redoBtn;
	private IconButton homeBtn;
	private IconButton dragBtn;
	private IconButton fullscreenButton;
	private final Runnable deselectDragBtn = () -> {
			if (dragBtn != null && controller.getApp().getMode()
					== EuclidianConstants.MODE_TRANSLATEVIEW) {
				dragBtn.setActive(false);
				controller.getApp().setMode(EuclidianConstants.MODE_SELECT_MOW);
			}
		};

	/**
	 * constructor
	 * @param appW - application
	 */
	public NotesTopbar(AppW appW) {
		this.appletParams = appW.getAppletParameters();
		controller = new TopbarController(appW, deselectDragBtn);
		if (appW.getActiveEuclidianView() != null) {
			appW.getActiveEuclidianView().getEuclidianController().addZoomerListener(this);
		}
		addStyleName("topbar");
		buildGui();
	}

	private void buildGui() {
		addMenuButton();
		addUndoRedo();
		addZoomButtons();
		addFullscreenButton();
		addSettingsButton();
	}

	private void addMenuButton() {
		if (!GlobalHeader.isInDOM() && appletParams.getDataParamShowMenuBar(false)) {
			addSmallPressButton(MaterialDesignResources.INSTANCE.toolbar_menu_black(), "Menu",
					() -> controller.onMenuToggle(), AccessibilityGroup.MENU);
			addDivider();
		}
	}

	private void addUndoRedo() {
		if (appletParams.getDataParamEnableUndoRedo()) {
			undoBtn = addSmallPressButton(MaterialDesignResources.INSTANCE.undo_border(), "Undo",
					() -> controller.onUndo(), AccessibilityGroup.UNDO);
			redoBtn = addSmallPressButton(MaterialDesignResources.INSTANCE.redo_border(), "Redo",
					() -> controller.onRedo(), AccessibilityGroup.REDO);
			addDivider();
		}
	}

	private void addZoomButtons() {
		if (!ZoomPanel.needsZoomButtons(controller.getApp())) {
			return;
		}

		if (!NavigatorUtil.isMobile()) {
			addSmallPressButton(GuiResourcesSimple.INSTANCE.zoom_in(), "ZoomIn.Tool",
					() -> controller.onZoomIn(), AccessibilityGroup.ZOOM_NOTES_PLUS);
			addSmallPressButton(GuiResourcesSimple.INSTANCE.zoom_out(), "ZoomOut.Tool",
					() -> controller.onZoomOut(), AccessibilityGroup.ZOOM_NOTES_MINUS);
		}

		homeBtn = addSmallPressButton(ZoomPanelResources.INSTANCE.home_zoom_black18(),
				"StandardView", () -> controller.onHome(), AccessibilityGroup.ZOOM_NOTES_HOME);
		homeBtn.setDisabled(true);

		addDragButton();
		addDivider();
	}

	private void addDragButton() {
		dragBtn = new IconButton(controller.getApp(), MaterialDesignResources
				.INSTANCE.move_canvas(), "PanView", "PanView", () -> controller.onDrag(),
				() -> controller.getApp().setMode(EuclidianConstants.MODE_SELECT_MOW));
		registerFocusable(dragBtn, AccessibilityGroup.ZOOM_NOTES_DRAG_VIEW);
		styleAndRegisterTopbarButton(dragBtn);
	}

	private void styleAndRegisterTopbarButton(IconButton button) {
		button.addStyleName("small");
		buttons.add(button);
		add(button);
	}

	/**
	 * update style of undo+redo buttons
	 * @param kernel - kernel
	 */
	public void updateUndoRedoActions(Kernel kernel) {
		kernel.getConstruction().getUndoManager().setAllowCheckpoints(
				appletParams.getParamAllowUndoCheckpoints());
		undoBtn.setDisabled(!kernel.undoPossible());
		redoBtn.setDisabled(!kernel.redoPossible());
	}

	private void addFullscreenButton() {
		if (controller.needsFullscreenButton()) {
			fullscreenButton = addSmallPressButton(ZoomPanelResources.INSTANCE
					.fullscreen_black18(), "Fullscreen", null,
					AccessibilityGroup.FULL_SCREEN_NOTES);
			fullscreenButton.addFastClickHandler(source ->
					controller.onFullscreenOn(fullscreenButton));

			addDivider();
		}
	}

	private void addSettingsButton() {
		if (controller.getApp().allowStylebar()) {
			IconButton settingsBtn = addSmallPressButton(MaterialDesignResources.INSTANCE.gear(),
					"Settings", null, AccessibilityGroup.SETTINGS_NOTES);
			settingsBtn.addFastClickHandler(source -> controller.onSettingsOpen(settingsBtn));
		}
	}

	private IconButton addSmallPressButton(SVGResource image, String ariaLabel,
			Runnable clickHandler, AccessibilityGroup group) {
		IconButton button = new IconButton(controller.getApp(), clickHandler, image, ariaLabel);
		add(button);
		buttons.add(button);
		controller.registerFocusable(button, group);

		return button;
	}

	private void addDivider() {
		SimplePanel divider = new SimplePanel();
		divider.addStyleName("divider");
		add(divider);
	}

	@Override
	public void setLabels() {
		buttons.forEach(SetLabels::setLabels);
	}

	@Override
	public void onCoordSystemChanged() {
		controller.updateHomeButtonVisibility(homeBtn);
	}

	public void deselectDragButton() {
		deselectDragBtn.run();
	}

	private void registerFocusable(IconButton button, AccessibilityGroup group) {
		controller.registerFocusable(button, group);
	}
}
