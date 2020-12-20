grammar Grammar1;

p0: T1 p2 EOF;
p2: T1 T3 | T3 T1;

T1: 'a';
T3: 'b';
