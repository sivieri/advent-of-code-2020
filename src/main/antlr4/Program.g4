grammar Program;

p3: T4 T5 | T5 T4;
p2: T4 T4 | T5 T5;
p1: p2 p3 | p3 p2;
p0: T4 p1 T5 EOF;
T5: 'b';
T4: 'a';