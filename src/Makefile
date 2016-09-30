JCC = javac
JFLAGS = -g

default: Game.class Controller.java

Game.class: Game.java
	$(JCC) $(JFLAGS) Game.java

Controller.class: Controller.java
	$(JCC) $(JFLAGS) Controller.java

clean:
	$(RM) *.class

run:
	javac *.java && java Game && make clean