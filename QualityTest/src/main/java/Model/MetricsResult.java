package Model;

public class MetricsResult {

  private String MetricsPath;

  public MetricsResult(String metricPath) {
    MetricsPath = metricPath;
  }

  public String getMetricsPath() {
    return MetricsPath;
  }

  public void setMetricsPath(String metricsPath) {
    MetricsPath = metricsPath;
  }
}
