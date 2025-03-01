package telsos.logging.impl;

import java.util.logging.Logger;

import telsos.logging.Level;
import telsos.logging.Log;
import telsos.logging.LogFactory;

public final class LogFactoryForLogger implements LogFactory<Logger> {

  @Override
  public Log getLog(Logger logger) {
    return new Log() {
      @Override
      public void logImpl(Level level, String message) {
        logger.log(asTargetLevel(level), message);
      }

      @Override
      public void logImpl(Level level, String message, Throwable throwable) {
        logger.log(asTargetLevel(level), message, throwable);
      }
    };
  }

  private static java.util.logging.Level asTargetLevel(Level level) {
    return switch (level) {
      case TRACE -> java.util.logging.Level.FINEST;
      case DEBUG -> java.util.logging.Level.FINE;
      case INFO  -> java.util.logging.Level.INFO;
      case WARN  -> java.util.logging.Level.WARNING;
      case ERROR -> java.util.logging.Level.SEVERE;
      case FATAL -> java.util.logging.Level.SEVERE;
    };
  }

}
