# HibernateMusing

Demonstration of developing DAO layer using TDD practices.

Guidance is taken from the book "TEST DRIVEN" by Lasse Koskela

Integration tests are important to understand if your queries are working fine which is not possible by using mock objects alone.
But overdoing it will cause the execution delays. So the god idea would be to test the happy path of the queries using integration tests and for testing exceptional situation mock objects should be used.
In memory HSQLDB is configured for the integration tests


