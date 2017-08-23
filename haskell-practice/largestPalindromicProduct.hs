main = putStrLn (show computeProducts)

computeProducts =
  maximum (filter palindrome [ i*j | i <- [1..999], j <- [1..999] ])

palindrome :: Integer -> Bool
palindrome x = show x == reverse (show x)