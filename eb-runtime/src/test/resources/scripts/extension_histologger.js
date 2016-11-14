activitydef = {
    "alias" : "testhistologger",
    "type" : "diag",
    "cycles" : "50000",
    "threads" : "20",
    "interval" : "2000",
    "targetrate" : "10000.0"
};

histologger.logHistoIntervals("testing extention histologger", ".*", "hdrhistolog.hdrlog", "0.5s");
print("started logging to hdrhistolog.hdrlog for all metrics at 1/2 second intervals.\n");

scenario.start(activitydef);
scenario.waitMillis(2000);
scenario.stop(activitydef);
