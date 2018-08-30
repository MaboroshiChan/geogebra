package org.geogebra.web.html5.gui.util;

import org.geogebra.common.euclidian.event.PointerEventType;
import org.geogebra.common.gui.SetLabels;
import org.geogebra.web.html5.Browser;
import org.geogebra.web.html5.css.ZoomPanelResources;
import org.geogebra.web.html5.gui.FastClickHandler;
import org.geogebra.web.html5.main.AppW;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author csilla
 *
 */
public class ZoomPanelMow extends FlowPanel implements SetLabels {

	private AppW appW;
	private StandardButton dragPadBtn;
	private StandardButton zoomInBtn;
	private StandardButton zoomOutBtn;
	private StandardButton homeBtn;
	private ZoomController zoomController;

	/**
	 * @param app
	 *            see {@link AppW}
	 */
	public ZoomPanelMow(AppW app) {
		this.appW = app;
		zoomController = new ZoomController(appW);
		buildGui();
	}

	private void buildGui() {
		addStyleName("mowZoomPanel");
		addDragPadButton();
		addZoomButtons();
	}

	/**
	 * @return zoom controller
	 */
	public ZoomController getZoomController() {
		return zoomController;
	}

	private void addDragPadButton() {
		dragPadBtn = new StandardButton(
				ZoomPanelResources.INSTANCE.move_canvas(), null, 24, appW);
		dragPadBtn.setStyleName("zoomPanelBtn");
		FastClickHandler handlerHome = new FastClickHandler() {

			@Override
			public void onClick(Widget source) {
				// TODO set drag canvas mode
			}
		};
		dragPadBtn.addFastClickHandler(handlerHome);
		add(dragPadBtn);
		// click handler
		ClickStartHandler.init(this, new ClickStartHandler(true, true) {

			@Override
			public void onClickStart(int x, int y, PointerEventType type) {
				// to stopPropagation and preventDefault.
			}
		});
	}

	/**
	 * Add zoom in/out buttons to GUI
	 */
	private void addZoomButtons() {
		if (!Browser.isMobile()) {
			addZoomInButton();
			addZoomOutButton();
		}
		homeBtn = new StandardButton(
				ZoomPanelResources.INSTANCE.home_zoom_black18(), null, 20,
				appW);
		homeBtn.setStyleName("zoomPanelBtn");
		homeBtn.addStyleName("zoomPanelBtnSmall");
		// hideHomeButton();
		FastClickHandler handlerHome = new FastClickHandler() {

			@Override
			public void onClick(Widget source) {
				getZoomController().onHomePressed();
			}
		};
		homeBtn.addFastClickHandler(handlerHome);
		add(homeBtn);
		// click handler
		ClickStartHandler.init(this, new ClickStartHandler(true, true) {

			@Override
			public void onClickStart(int x, int y, PointerEventType type) {
				// to stopPropagation and preventDefault.
			}
		});
	}

	private void addZoomOutButton() {
		zoomOutBtn = new StandardButton(
				ZoomPanelResources.INSTANCE.zoomout_black24(), null, 24, appW);
		zoomOutBtn.setStyleName("zoomPanelBtn");

		FastClickHandler handlerZoomOut = new FastClickHandler() {
			@Override
			public void onClick(Widget source) {
				getZoomController().onZoomOutPressed();
			}
		};
		zoomOutBtn.addFastClickHandler(handlerZoomOut);
		add(zoomOutBtn);
	}

	private void addZoomInButton() {
		zoomInBtn = new StandardButton(
				ZoomPanelResources.INSTANCE.zoomin_black24(), null, 24, appW);
		zoomInBtn.setStyleName("zoomPanelBtn");

		FastClickHandler handlerZoomIn = new FastClickHandler() {
			@Override
			public void onClick(Widget source) {
				getZoomController().onZoomInPressed();
			}
		};
		zoomInBtn.addFastClickHandler(handlerZoomIn);
		add(zoomInBtn);
	}

	/**
	 * Sets translated titles of the buttons.
	 */
	public void setLabels() {
		setButtonTitleAndAltText(dragPadBtn, "Drag Pad");
		setButtonTitleAndAltText(homeBtn, "StandardView");
		setButtonTitleAndAltText(zoomOutBtn, "ZoomOut.Tool");
		setButtonTitleAndAltText(zoomInBtn, "ZoomIn.Tool");
	}

	private void setButtonTitleAndAltText(StandardButton btn, String string) {
		if (btn != null) {
			btn.setTitle(appW.getLocalization().getMenu(string));
			btn.setAltText(appW.getLocalization().getMenu(string));
		}
	}
}
