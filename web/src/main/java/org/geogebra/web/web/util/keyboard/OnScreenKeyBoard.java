package org.geogebra.web.web.util.keyboard;

import java.util.ArrayList;

import org.geogebra.common.euclidian.event.PointerEventType;
import org.geogebra.common.util.Language;
import org.geogebra.web.html5.gui.tooltip.ToolTipManagerW;
import org.geogebra.web.html5.gui.view.algebra.MathKeyboardListener;
import org.geogebra.web.html5.main.AppW;
import org.geogebra.web.html5.main.LocalizationW;
import org.geogebra.web.html5.util.keyboard.VirtualKeyboard;
import org.geogebra.web.web.util.keyboardBase.KBBase;
import org.geogebra.web.web.util.keyboardBase.KeyBoardButtonBase;
import org.geogebra.web.web.util.keyboardBase.KeyBoardButtonFunctionalBase;
import org.geogebra.web.web.util.keyboardBase.KeyBoardProcessable;
import org.geogebra.web.web.util.keyboardBase.KeyBoardProcessable.ArrowType;

import com.google.gwt.core.client.Scheduler;

/**
 * on screen keyboard containing mathematical symbols and formulas
 */
public class OnScreenKeyBoard extends KBBase implements
		VirtualKeyboard {

	/**
	 * set whether the keyboard is used at the moment or not
	 * 
	 * @param used
	 *            whether the keyboard is used or not
	 */
	public void setUsed(boolean used) {
		// TODO only required for AutoCompleteTextFieldW
		if (this.processField instanceof TextFieldProcessing) {
			((TextFieldProcessing) this.processField).setKeyBoardUsed(used
					&& this.contentNumber.isVisible());
		}
	}

	/**
	 * should not be called; use getInstance instead
	 * 
	 * @param appW
	 */
	public OnScreenKeyBoard(AppW appW) {
		super(true);
		this.app = appW;
		this.loc = (LocalizationW) app.getLocalization(); // TODO
		addStyleName("KeyBoard");
		createKeyBoard();
		initAccentAcuteLetters();
		initAccentGraveLetters();
		initAccentCaronLetters();
		initAccentCircumflexLetters();
	}

	public void addSupportedLocale(Language gwtLang, String language) {
		supportedLocales.put(gwtLang.localeGWT, language);
	}

	@Override
	public void onClick(KeyBoardButtonBase btn, PointerEventType type) {

		// TODO
		ToolTipManagerW.hideAllToolTips();

		if (btn instanceof KeyBoardButtonFunctionalBase) {
			KeyBoardButtonFunctionalBase button = (KeyBoardButtonFunctionalBase) btn;

			switch (button.getAction()) {
			case SHIFT:
				removeAccents();
				processShift();
				break;
			case BACKSPACE:
				processField.onBackSpace();
				break;
			case ENTER:
				// make sure enter is processed correctly
				processField.onEnter();
				if (processField.resetAfterEnter()) {
					updateKeyBoardListener.keyBoardNeeded(false, null);
				}
				break;
			case ARROW_LEFT:
				processField.onArrow(ArrowType.left);
				break;
			case ARROW_RIGHT:
				processField.onArrow(ArrowType.right);
				break;
			case SWITCH_KEYBOARD:
				String caption = button.getCaption();
				if (caption.equals(GREEK)) {
					setToGreekLetters();
				} else if (caption.equals(NUMBER)) {
					setKeyboardMode(KeyboardMode.NUMBER);
				} else if (caption.equals(TEXT)) {
					if (greekActive) {
						greekActive = false;
						switchABCGreek.setCaption(GREEK);
						loadLang(this.keyboardLocale);
					}
					if (shiftIsDown) {
						processShift();
					}
					if (accentDown) {
						removeAccents();
					}
					setKeyboardMode(KeyboardMode.TEXT);
				} else if (caption.equals(SPECIAL_CHARS)) {
					setKeyboardMode(KeyboardMode.SPECIAL_CHARS);
				}
			}
		} else {

			String text = btn.getFeedback();

			if (isAccent(text)) {
				processAccent(text, btn);
			} else {
				processField.insertString(text);
				if (accentDown) {
					removeAccents();
				}
			}

			if (shiftIsDown && !isAccent(text)) {
				processShift();
			}

			processField.setFocus(true);
		}

		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
			@Override
			public void execute() {
				Scheduler.get().scheduleDeferred(
						new Scheduler.ScheduledCommand() {
							@Override
							public void execute() {
								processField.scrollCursorIntoView();
							}
						});
			}
		});
	}

	/**
	 * The text field to be used
	 * 
	 * @param textField
	 *            the text field connected to the keyboard
	 */
	public void setTextField(MathKeyboardListener textField) {
		if (textField instanceof KeyBoardProcessable) {
			this.processField = (KeyBoardProcessable) textField;
		} else {
			this.processField = new TextFieldProcessing();
			((TextFieldProcessing) this.processField).setField(textField);
		}
	}

	/**
	 * @param mode
	 *            the keyboard mode
	 */
	@Override
	public void setKeyboardMode(final KeyboardMode mode) {
		this.mode = mode;
		if (mode == KeyboardMode.NUMBER) {
			// TODO required for AutoCompleteTextFieldW
			// processField.setKeyBoardModeText(false);
			contentNumber.setVisible(true);
			contentLetters.setVisible(false);
			contentSpecialChars.setVisible(false);
		} else if (mode == KeyboardMode.TEXT) {
			greekActive = false;
			contentNumber.setVisible(false);
			contentLetters.setVisible(true);
			contentSpecialChars.setVisible(false);
			// TODO required for AutoCompleteTextFieldW
			// processField.setKeyBoardModeText(true);
			updateKeyBoardListener.showInputField();
		} else if (mode == KeyboardMode.SPECIAL_CHARS) {
			// TODO required for AutoCompleteTextFieldW
			// processField.setKeyBoardModeText(false);
			contentNumber.setVisible(false);
			contentLetters.setVisible(false);
			contentSpecialChars.setVisible(true);
		}
		setUsed(true);
	}

	/**
	 * updates the keys to the given language
	 * 
	 * @param updateSection
	 *            "lowerCase" or "shiftDown"
	 * @param language
	 *            String
	 */
	@Override
	protected void updateKeys(String updateSection, String language) {
		// update letter keys
		ArrayList<KeyBoardButtonBase> buttons = this.letters.getButtons();
		for (int i = 0; i < NUM_LETTER_BUTTONS; i++) {
			KeyBoardButtonBase button = buttons.get(i);
			if (!(button instanceof KeyBoardButtonFunctionalBase)) {
				String newCaption = getNewCaption(generateKey(i),
						updateSection, language);
				if (newCaption.equals("")) {
					button.setVisible(false);
					button.getElement().getParentElement()
							.addClassName("hidden");
				} else {
					button.setVisible(true);
					button.getElement().getParentElement()
							.removeClassName("hidden");
					button.setCaption(newCaption);
				}
			}
		}

		// update e.g. button with sin/cos/tan according to the new language
		for (KeyBoardButtonBase b : updateButton.keySet()) {
			String captionPlain = updateButton.get(b);
			if (captionPlain.endsWith("^-1")) {
				// e.g. for "sin^-1" only "sin" is translated
				captionPlain = captionPlain.substring(0,
						captionPlain.lastIndexOf("^-1"));
				// always use the English output (e.g. "arcsin")
				b.setCaption(loc.getPlain(captionPlain) + "^-1", false);
			} else {
				// use language specific output
				b.setCaption(loc.getPlain(captionPlain), true);
			}
		}

		processField.updateForNewLanguage(loc);

		checkStyle();
	}

	@Override
	protected String getNewCaption(String key, String updateSection,
			String language) {
		return ((AppW) app).getKey(key, updateSection, language);
	}

	@Override
	public void show() {
		this.keyboardWanted = true;
		updateSize();
		checkLanguage();
		setStyleName();//maybe not needed always, but definitely in Win8 app
		super.show();
	}
}