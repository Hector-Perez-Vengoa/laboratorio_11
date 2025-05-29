package com.tecsup.petclinic.exception;

public class OwnerNotFoundException extends Exception {

  public OwnerNotFoundException() {
    super();
  }

  public OwnerNotFoundException(String message) {
    super(message);
  }
}
