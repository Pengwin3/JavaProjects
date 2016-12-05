JCOPTIONS=-g -nowarn
JOPTIONS=
USERHOME=$(HOME)

.SUFFIXES:
.SUFFIXES: .class .java
#.java.class: export CLASSPATH; CLASSPATH=$(CLASSPATH); $(JAVAPATH)/javac -nowarn $(JOPTIONS) $*.java
.java.class: javac $(JCOPTIONS) $*.java

#
# As you define new classes, add them to the following list.
# It may not be absolutely necessary, but it will help guarantee that
# necessary recompilation gets done.
#
ASST=class_glass
TOPPACKAGE=StainedGlass/
TARGET=StainedGlass/Reconstructor
CLASSES:=$(patsubst %.java,%.class,$(wildcard $(TOPPACKAGE)*.java))

all: $(TARGET).class


%.class: %.java
	$(JAVAPATH)javac -nowarn $(JOPTIONS) $*.java

setup:
	cp *.java $(TOPPACKAGE)
