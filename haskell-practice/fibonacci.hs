main = do
  putStrLn "Enter nth fibonacci number:"
  response <- getLine
  print (fib (read response :: Integer))
  main

fib :: Integer -> Integer
fib x
  | x == 1    = 0
  | x == 2    = 1
  | otherwise = fib (x - 1) + fib (x - 2)
