package efs.task.reflection.model;

public interface Fighter {

  default int getDamage() {
    return 0;
  }
}
