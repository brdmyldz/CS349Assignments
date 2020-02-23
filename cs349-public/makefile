export FILENAME=HelloFX
export JAVA_FX_HOME='/Users/javery/lib/javafx-sdk-11.0.2'

default:
	javac --module-path $(JAVA_FX_HOME)/lib --add-modules javafx.controls,javafx.graphics,javafx.base,javafx.fxml,javafx.media *.java
run: default
	java --module-path $(JAVA_FX_HOME)/lib --add-modules javafx.controls,javafx.graphics,javafx.base,javafx.fxml,javafx.media $(FILENAME)
clean:
	rm *.class