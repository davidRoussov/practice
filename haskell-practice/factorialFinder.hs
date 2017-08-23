import Data.List

main = do
  putStrLn "compute factorial of: "
  response <- getLine
  putStrLn (show (factorialRecurse (read response)))

factorialRecurse :: Integer -> Integer
factorialRecurse 0 = 1
factorialRecurse x = x * factorialRecurse(x - 1)

factorialFold :: Integer -> Integer
factorialFold x = foldl (*) 1 [1..x]
