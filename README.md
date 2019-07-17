# data-wrangler
A java helper api for developers working with unstructured data mining

Data wrangler provides a data processor for developers to parse and format multiple data patterns.

Check out Test class for example code. 

```java
 Wrangler wrangler = new Wrangler
                .WranglerBuilder("(?<date>(\\w+\\s)+(.\\d+)+)" + "\\s*" + "(?<loggingHost>[A-Za-z0-9._%-]+)" + "\\s*" + "(?<info1>[A-Za-z0-9._%-]+)" +"\\s*"+"(?<EventTime>(\\d+\\W+\\d)+\\d)" +"\\s*"+"(;\"*)(((?<EventType>(.*?))\\\"*;))"+"\\s*"+"(\"*)(((?<Severity>(.*?))\\\"*;))"+"\\s*"+"(\"*)(((?<Channel>(.*?))\\\"*;))"+"\\s*"+"(\"*)(((?<Hostname>(.*?))\\\"*;))"+"\\s*"+"(\"*)(((?<EventID>(.*?))\\\"*;))"+"\\s*"+"(\"*)(((?<SourceName>(.*?))\\\"*;))"+"\\s*"+"(\"*)(((?<AccountName>(.*?))\\\"*;))"+"\\s*"+"(\"*)(((?<AccountType>(.*?))\\\"*;))"+"\\s*"+"(\"*)(((?<Domain>(.*?))\\\"*;))"+ "\\s*" + "(\"*)(?<Message>(.*))" )
                .extractField("date")
                .extractField("Hostname")
                .extractField("EventID")
                .extractField("EventTime")
                .extractField("Message")
                .formatField("EventID", Integer.TYPE)
                .formatDateField("EventTime","yyyy-MM-dd hh:mm:ss","yyyy-MMM-dd hh:mm:ss")
                .build();

        wrangler.process("Aug  4 08:03:52 host0001 LOG 2018-08-04 02:03:52;\"Application\";\"INFO\";\"next-gen\";\"host0001\";8347;\"next-gen\";\"-\";\"-\";\"-\";\"The service is alive.\"");
        System.out.println(wrangler.toJson());
```
