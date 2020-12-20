grammar Grammar2;

p0: T4 p1 T5 EOF;
p1: p2 p3 | p3 p2;
p2: T4 T4 | T5 T5;
p3: T4 T5 | T5 T4;

T4: 'a';
T5: 'b';
