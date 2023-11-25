package br.edu.ifpi.enums;

public enum StatusCurso {
  ABERTO, FECHADO, CURSANDO;

  public static StatusCurso fromString(String statusStr) {
    for (StatusCurso status : StatusCurso.values()) {
      if (status.name().equalsIgnoreCase(statusStr)) {
        return status;
      }
    }
    throw new IllegalArgumentException("No enum constant br.edu.ifpi.enums.StatusCurso." + statusStr);
  }
}